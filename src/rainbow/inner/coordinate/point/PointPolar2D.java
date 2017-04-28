package rainbow.inner.coordinate.point;

import static java.lang.Math.*;

/**
 * 这是二维的极坐标点,用于平面极坐标系
 *
 * @author Rainbow Yang
 */
public class PointPolar2D implements MyPoint<PointPolar2D> {
    private double r;
    private double angle;

    public PointPolar2D(double r, double angle) {
        this.r = r;
        this.angle = angle;
    }

    public double getR() {
        return r;
    }

    public double getAngle() {
        return angle;
    }

    /**
     * 极坐标相加后,角度的取值范围是(-PI,PI]
     *
     * @param pt 需要相加的点
     * @return 相加后的点
     */
    @Override
    public PointPolar2D add(PointPolar2D pt) {
        return add(pt.r, pt.angle);
    }

    public PointPolar2D add(double r, double angle) {
        double rcos = r * cos(angle) + this.r * cos(this.angle);
        double rsin = r * sin(angle) + this.r * sin(this.angle);
        return new PointPolar2D(sqrt(rcos * rcos + rsin * rsin), Math.atan2(rsin, rcos));
    }

    @Override
    public PointPolar2D reduce(PointPolar2D pt) {
        return add(pt.r, pt.angle + PI);
    }

    public PointPolar2D reduce(double r, double angle) {
        return add(r, angle + PI);
    }

    @Override
    public PointPolar2D spin(double angle) {
        return new PointPolar2D(r, this.angle + angle);
    }

    @Override
    public PointPolar2D spin(PointPolar2D center, double angle) {
        return reduce(center).spin(angle).add(center);
    }

    @Override
    public PointPolar2D times(double times) {
        return new PointPolar2D(r * times, angle);
    }

    @Override
    public String toString() {
        return "PointPolar2D{" +
                "r=" + r +
                ", angle=" + angle +
                '}';
    }
}
