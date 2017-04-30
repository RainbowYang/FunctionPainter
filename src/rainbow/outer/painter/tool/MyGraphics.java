package rainbow.outer.painter.tool;

import rainbow.inner.coordinate.point.MyPoint;
import rainbow.inner.coordinate.point.PointDouble;
import rainbow.inner.coordinate.point.PointForAxes;
import rainbow.inner.coordinate.system.comp.LocationChanger;
import rainbow.inner.math.Line;
import rainbow.inner.system.MySystem;

import java.awt.*;
import java.util.Arrays;
import java.util.List;


/**
 * 包装Graphics，在内部自动进行坐标转换
 * 所有方法均返回this，以用于链式编程。
 *
 * @author Rainbow Yang
 * @see rainbow.inner.coordinate.system.CoordinateSystem
 */
public class MyGraphics {
    public static final String MODE_STRAIGHT_LINE = "Straight Line";
    public static final String MODE_LINE_SEGMENT = "Line Segment";
    public static final String MODE_RAY_LINE = "Ray Line";

    private Graphics g;
    private LocationChanger changer;

    public MyGraphics(Graphics g) {
        this.g = g;
        changer = MySystem.getSystem().getCoordinateSystem().getLocationChanger();
    }

    public Graphics getGraphics() {
        return g;
    }

    public MyGraphics paintPoints(MyPoint... ps) {
        return paintPoints(Arrays.asList(ps));
    }

    public MyGraphics paintPoints(List<MyPoint> ps) {
        Polygon p = new Polygon();
        for (PointDouble point : changer.toReal(ps)) {
            p.addPoint((int) point.getX(), (int) point.getY());
        }
        g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
        return this;
    }

    public MyGraphics fillPoint(MyPoint... ps) {
        return fillPoint(Arrays.asList(ps));
    }

    public MyGraphics fillPoint(List<MyPoint> ps) {
        Polygon p = new Polygon();
        for (PointDouble point : changer.toReal(ps)) {
            p.addPoint((int) point.getX(), (int) point.getY());
        }
        g.fillPolygon(p);
        return this;
    }

    /**
     * 画出一个点的位置
     * 向各个方向做垂线，各个垂点继续做垂线
     * 比如三维就是一个长方体，二维是一个长方形
     *
     * @param p 要做位置的点
     * @return MyGraphics
     */
    public MyGraphics paintLocation(MyPoint p) {
        PointForAxes pa = (PointForAxes) p;
        for (int i = 0; i < pa.size(); i++) {
            if (pa.get(i) != 0) {
                PointForAxes p0 = pa.clear(i);
                paintLine(pa, p0);

                paintLocation(p0);
            }
        }

        return this;
    }

    public MyGraphics paintLine(PointDouble p1, PointDouble p2) {
        g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
        return this;
    }

    public MyGraphics paintLine(MyPoint p1, MyPoint p2) {
        paintLine(changer.toReal(p1), changer.toReal(p2));
        return this;
    }

    /**
     * 过两点画直线，线段，射线
     *
     * @param p1   两点之一
     * @param p2   两点之二
     * @param mode 直线，线段，射线
     * @return MyGraphics
     */
    public MyGraphics paintLine(MyPoint p1, MyPoint p2, String mode) {
        if (p1.equals(p2)) {
            return this;
        }
        if (mode == MODE_LINE_SEGMENT) {
            paintLine(p1, p2);
            return this;
        }

        PointDouble pd1 = changer.toReal(p1);
        PointDouble pd2 = changer.toReal(p2);

        if (pd1.getX() == pd2.getX()) {
            switch (mode) {
                case MODE_STRAIGHT_LINE:
                    g.drawLine((int) pd1.getX(), 0, (int) pd2.getX(), (int) MySystem.getHeight());
                    break;
                case MODE_RAY_LINE:
                    if (pd1.getY() < pd2.getY()) {
                        g.drawLine((int) pd1.getX(), (int) pd1.getY(), (int) pd2.getX(), (int) MySystem.getHeight());
                    } else {
                        g.drawLine((int) pd1.getX(), (int) pd1.getY(), (int) pd2.getX(), 0);
                    }
                    break;
            }
            return this;
        }


        Line line = new Line(pd1, pd2);
        //屏幕左边
        Line left = Line.Y_AXIS;
        //屏幕右边
        Line right = new Line(new PointDouble(MySystem.getWidth(), 0), new PointDouble(MySystem.getWidth(), MySystem.getHeight()));

        PointDouble start = line.getCross(left);
        PointDouble end = line.getCross(right);

        switch (mode) {
            case MODE_RAY_LINE:
                if (pd1.getX() > pd2.getX()) {
                    paintLine(pd1, start);
                } else {
                    paintLine(pd1, end);
                }
                break;
            case MODE_STRAIGHT_LINE:
                paintLine(start, end);
                break;
        }
        return this;

    }
}
