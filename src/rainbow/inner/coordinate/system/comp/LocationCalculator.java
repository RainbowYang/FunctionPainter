package rainbow.inner.coordinate.system.comp;

import rainbow.inner.coordinate.point.MyPoint;
import rainbow.inner.coordinate.point.PointDouble;
import rainbow.inner.coordinate.system.CoordinateSystem;

/**
 * 用于坐标的换算
 * 屏幕上的位置 和 在坐标中的位置
 *
 * @author Rainbow Yang
 * @date 2017/4/23
 */
public abstract class LocationCalculator {
    private CoordinateSystem system;

    public LocationCalculator(CoordinateSystem system) {
        this.system = system;
    }

    public abstract PointDouble[] toReal(MyPoint<? extends MyPoint>... ps);

    public abstract PointDouble toReal(MyPoint<? extends MyPoint> p);

    public abstract MyPoint<? extends MyPoint>[] toSystem(PointDouble... ps);

    public abstract MyPoint<? extends MyPoint> toReal(PointDouble p);
}
