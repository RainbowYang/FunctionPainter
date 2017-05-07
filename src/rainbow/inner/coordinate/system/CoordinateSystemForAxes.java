package rainbow.inner.coordinate.system;

import rainbow.inner.coordinate.point.MyPoint;
import rainbow.inner.coordinate.point.PointDouble;
import rainbow.inner.coordinate.point.PointForAxes;
import rainbow.inner.coordinate.system.comp.Listeners;
import rainbow.inner.coordinate.system.comp.LocationChanger;
import rainbow.inner.coordinate.system.comp.Range;
import rainbow.inner.coordinate.system.comp.SystemPainter;
import rainbow.inner.system.MySystem;
import rainbow.outer.frame.MainFrame;
import rainbow.outer.painter.tool.MyGraphics;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Arrays;

/**
 * 任意维度的轴坐标系
 * todo 待加强
 *
 * @author Rainbow Yang
 */
public class CoordinateSystemForAxes extends CoordinateSystem {

    public PointForAxes p0;

    //每个维度与三点钟方向沿逆时针方向的夹角
    private double[] angles;
    //每个维度的单位长度
    private double[] lengthes;

    public CoordinateSystemForAxes(int size) {
        angles = new double[size];
        lengthes = new double[size];
        init();
    }

    public CoordinateSystemForAxes(double... angles) {
        this.angles = angles;
        this.lengthes = new double[angles.length];
        init();
    }

    {
        x = MySystem.getSystem().getWidth() / 2;
        y = MySystem.getSystem().getHeight() / 2;
    }

    public void init() {
        p0 = new PointForAxes(0, size(), true);

        for (int i = 0; i < size(); i++) {
            lengthes[i] = 40;
        }
        switch (angles.length) {
            case 3:
                angles[2] = Math.PI * 5 / 4;
            case 2:
                angles[1] = Math.PI / 2;
            case 1:
                angles[0] = 0;
        }
    }

    @Override
    protected void initLocationChanger() {
        changer = new LocationChanger(this) {
            @Override
            public PointDouble toReal(MyPoint p) {
                PointForAxes pa = p.toPointForAxes();
                double px = x, py = y;//x,y是原点的在屏幕上的坐标
                for (int i = 0; i < size(); i++) {
                    px += Math.cos(angles[i]) * lengthes[i] * pa.get(i);
                    py -= Math.sin(angles[i]) * lengthes[i] * pa.get(i);
                }
                return new PointDouble(px, py);
            }
        };
    }

    @Override
    protected void initSystemPainter() {
        //todo 更多参数
        painter = new SystemPainter(this) {
            @Override
            public void paintNum(MyGraphics mg) {
                //todo Test
                for (int s = 0; s < size(); s++) {
                    for (int i = -0; i < 30; i++) {
                        if (i == 0)
                            continue;
                        mg.paintString(i + "", p0.add(s, i));
                    }
                }
            }

            @Override
            public void paintOrigin(MyGraphics mg) {
                mg.paintString("O", p0);
            }

            @Override
            public void paintAxes(MyGraphics mg) {
                mg.setColor(Color.RED);
                for (int i = 0; i < size(); i++) {
                    mg.paintLine(p0, p0.add(i, 1), MyGraphics.MODE_STRAIGHT_LINE);
                }
            }

            @Override
            public void paintGrid(MyGraphics mg) {
                //维度遍历
                for (int s = 0; s < size(); s++) {
                    //值遍历
                    //todo test
                    for (int i = -0; i < 30; i++) {
                        PointForAxes p = p0.add(s, i);
                        PointForAxes p1 = p.add(s == size() - 1 ? 0 : s + 1, 1);
                        PointForAxes p2 = p.add(s == 0 ? size() - 1 : s - 1, 1);
                        mg.paintLine(p, p1, MyGraphics.MODE_RAY_LINE);
                        mg.paintLine(p, p2, MyGraphics.MODE_RAY_LINE);
                    }
                }
            }
        };
    }

    @Override
    protected void initRange() {
        //todo 暂时
        range = new Range(this) {
            @Override
            public double getStart() {
                return -20;
            }

            @Override
            public double getStep() {
                return 0.01;
            }

            @Override
            public double getEnd() {
                return 20;
            }
        };
    }

    @Override
    protected void initListeners() {
        listeners = new Listeners(this) {
            //记录点击的点
            private double dx, dy;

            //缩放倍数
            private double times = 1.1;

            @Override
            public MouseAdapter getMouseAdapter() {
                return new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            dx = getX() - e.getX();
                            dy = getY() - e.getY();

                        }
                    }

                    //坐标系移动
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        setX(e.getX() + dx);
                        setY(e.getY() + dy);
                        MainFrame.mainFrame.repaint();
                    }

                    //缩放效果
                    @Override
                    public void mouseWheelMoved(MouseWheelEvent e) {
                        if (e.getWheelRotation() > 0) {
                            setLengthes(times);
                        } else {
                            setLengthes(1 / times);
                        }
                        MainFrame.mainFrame.repaint();
                    }
                };
            }
        };
    }

    public int size() {
        return angles.length;
    }

    public void setAngles(double[] angles) {
        this.angles = angles;
    }

    public void setAngle(int index, double angle) {
        this.angles[index] = angle;
    }

    public void setLengthes(double[] lengthes) {
        this.lengthes = lengthes;
    }

    public void setLength(int index, double length) {
        this.lengthes[index] = length;
    }

    public void setLengthes(double times) {
        for (int i = 0; i < size(); i++) {
            lengthes[i] = lengthes[i] * times;

        }
    }


    /**
     * 对调两个维度的位置
     *
     * @param index1 维度1
     * @param index2 维度2
     */
    public void change(int index1, int index2) {
        double a = angles[index1], l = lengthes[index1];
        angles[index1] = angles[index2];
        lengthes[index1] = lengthes[index2];
        angles[index2] = a;
        lengthes[index2] = l;
    }

    @Override
    public String toString() {
        return "CoordinateSystemForAxes{" +
                "angles=" + Arrays.toString(angles) +
                ", lengthes=" + Arrays.toString(lengthes) +
                '}';
    }
}
