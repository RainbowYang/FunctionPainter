package rainbow.inner.coordinate.system;

import rainbow.inner.coordinate.point.MyPoint;
import rainbow.inner.coordinate.point.PointDouble;
import rainbow.inner.coordinate.point.PointForAxes;
import rainbow.inner.coordinate.system.comp.LocationChanger;
import rainbow.inner.coordinate.system.comp.Range;
import rainbow.inner.coordinate.system.comp.SystemPainter;
import rainbow.outer.painter.tool.MyGraphics;

import java.awt.*;
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
    private double[] lengths;

    public CoordinateSystemForAxes(int size) {
        angles = new double[size];
        lengths = new double[size];
        init();
    }

    public CoordinateSystemForAxes(double... angles) {
        this.angles = angles;
        this.lengths = new double[angles.length];
        init();
    }

    public void init() {
        p0 = new PointForAxes(0, size(), true);

        for (int i = 0; i < size(); i++) {
            lengths[i] = 40;
        }
        switch (angles.length) {
            case 3:
                setAngles(0, Math.PI * 5 / 4, Math.PI / 2);
                break;
            case 2:
                setAngles(0, Math.PI / 2);
                break;
            case 1:
                setAngles(0);
                break;
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
                    px += Math.cos(angles[i]) * lengths[i] * pa.get(i);
                    py -= Math.sin(angles[i]) * lengths[i] * pa.get(i);
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

    }

    public int size() {
        return angles.length;
    }

    public void setAngles(double... angles) {
        this.angles = angles;
    }

    public void setAngle(int index, double angle) {
        this.angles[index] = angle;
    }

    public void setLengths(double[] lengths) {
        this.lengths = lengths;
    }

    public void setLength(int index, double length) {
        this.lengths[index] = length;
    }

    public void setLengths(double times) {
        for (int i = 0; i < size(); i++) {
            lengths[i] = lengths[i] * times;

        }
    }


    /**
     * 对调两个维度的位置
     *
     * @param index1 维度1
     * @param index2 维度2
     */
    public void change(int index1, int index2) {
        double a = angles[index1], l = lengths[index1];
        angles[index1] = angles[index2];
        lengths[index1] = lengths[index2];
        angles[index2] = a;
        lengths[index2] = l;
    }

    @Override
    public String toString() {
        return "CoordinateSystemForAxes{" +
                "angles=" + Arrays.toString(angles) +
                ", lengths=" + Arrays.toString(lengths) +
                '}';
    }
}
