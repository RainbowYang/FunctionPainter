package rainbow.inner.coordinate.system;

import rainbow.inner.coordinate.point.MyPoint;
import rainbow.inner.coordinate.point.PointDouble;
import rainbow.inner.coordinate.point.PointForAxes;
import rainbow.inner.coordinate.system.comp.Axes;
import rainbow.inner.coordinate.system.comp.LocationChanger;
import rainbow.inner.coordinate.system.comp.SystemPainter;
import rainbow.inner.coordinate.system.comp.special.Rotate3DEventListener;
import rainbow.inner.view.graphics.MyGraphics;

import java.awt.*;

import static java.lang.Math.*;

/**
 * 任意维度的轴坐标系
 *
 * @author Rainbow Yang
 */
public class CoordinateSystemForAxes extends CoordinateSystem {

    private PointForAxes p0;

    public CoordinateSystemForAxes(int size) {
        getAxes().addAxes(size);
        init();
    }

    private void init() {
        p0 = new PointForAxes(0, getAxes().getSize(), true);

        switch (getAxes().getSize()) {
            case 4:
                getAxes().setAngle(0, PI * 5 / 4);
                getAxes().setAngle(1, 0);
                getAxes().setAngle(2, PI / 2);
                getAxes().setAngle(3, PI * 3 / 4);
            case 3:
                getAxes().setAngle(0, PI * 5 / 4);
                getAxes().setAngle(1, 0);
                getAxes().setAngle(2, PI / 2);
                break;
            case 2:
                getAxes().setAngle(0, 0);
                getAxes().setAngle(1, PI / 2);
                break;
            case 1:
                getAxes().setAngle(0, 0);
                break;
        }

        if (getAxes().getSize() >= 3)
            setComp(new Rotate3DEventListener(this));
    }

    {
        setComp(new LocationChanger(this) {
            @Override
            public PointDouble toReal(MyPoint p) {
                Axes axes = getAxes();

                PointForAxes pa = p.toPointForAxes();
                double px = getX(), py = getY();//x,y是原点的在屏幕上的坐标
                for (int i = 0; i < getAxes().getSize(); i++) {
                    double angle = axes.getAngle(i);
                    double length = axes.getLength(i);
                    px += cos(angle) * length * pa.get(i);
                    py -= sin(angle) * length * pa.get(i);
                }
                return new PointDouble(px, py);
            }

            @Override
            public MyPoint toSystem(PointDouble p) {
                PointDouble dp = new PointDouble(p.getX() - getX(), getY() - p.getY());
                double x = dp.spin(PI / 2 - getAxes().getAngle(1)).getX();
                double y = dp.spin(0 - getAxes().getAngle(0)).getY();

                double xAngle = getAxes().getAngle(0) + PI / 2 - getAxes().getAngle(1);
                double yAngle = getAxes().getAngle(1) - getAxes().getAngle(0);

                double px = x / cos(xAngle) / getAxes().getLength(0);
                double py = y / sin(yAngle) / getAxes().getLength(1);

                return new PointForAxes(px, py);
            }
        });

        setComp(new SystemPainter(this) {
            @Override
            public void paintNum(MyGraphics mg) {
                //todo Test
                for (int s = 0; s < getAxes().getSize(); s++) {
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
                for (int i = 0; i < getAxes().getSize(); i++) {
                    mg.paintLine(p0, p0.add(i, 1), MyGraphics.MODE_STRAIGHT_LINE);
                }
            }

            @Override
            public void paintGrid(MyGraphics mg) {
                // //维度遍历
                // for (int s = 0; s < getAxes().getSize(); s++) {
                //     //值遍历
                //     //todo test
                //     for (int i = -0; i < 50; i++) {
                //         PointForAxes p = p0.add(s, i);
                //         PointForAxes p1 = p.add(s == getAxes().getSize() - 1 ? 0 : s + 1, 1);
                //         PointForAxes p2 = p.add(s == 0 ? getAxes().getSize() - 1 : s - 1, 1);
                //         mg.paintLine(p, p1, MyGraphics.MODE_RAY_LINE);
                //         mg.paintLine(p, p2, MyGraphics.MODE_RAY_LINE);
                //     }
                // }
            }
        });
    }
}
