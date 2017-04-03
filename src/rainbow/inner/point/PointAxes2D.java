package rainbow.inner.point;

import static java.lang.Math.sin;
import static java.lang.Math.cos;

/**
 * 这是二维的轴坐标点,用于平面直角坐标系
 *
 * @author Rainbow Yang
 * @date 2017/4/2
 */
public class PointAxes2D implements MyPoint<PointAxes2D> {
    private double x;
    private double y;

    public PointAxes2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public PointAxes2D add(PointAxes2D pt) {
        return add(pt.x, pt.y);
    }

    public PointAxes2D add(double x, double y) {
        return new PointAxes2D(this.x + x, this.y + y);
    }

    @Override
    public PointAxes2D reduce(PointAxes2D pt) {
        return add(-pt.x, -pt.y);
    }

    public PointAxes2D reduce(double x, double y) {
        return add(-x, -y);
    }

    @Override
    public PointAxes2D spin(double angle) {
        return new PointAxes2D(x * cos(angle) - y * sin(angle), x * sin(angle) + y * cos(angle));
    }

    @Override
    public PointAxes2D spin(PointAxes2D center, double angle) {
        return reduce(center).spin(angle).add(center);
    }

    @Override
    public PointAxes2D times(double times) {
        return new PointAxes2D(x * times, y * times);
    }

    @Override
    public String getDescription(String name) {
        return null;
    }

    @Override
    public String toString() {
        return "PointAxes2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
