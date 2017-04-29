package rainbow.inner.coordinate.system.comp;

import rainbow.inner.coordinate.point.MyPoint;
import rainbow.inner.coordinate.point.PointDouble;
import rainbow.inner.coordinate.system.CoordinateSystem;

import java.util.List;

/**
 * 用于坐标的换算
 * 屏幕上的位置 和 在坐标中的位置
 *
 * @author Rainbow Yang
 */
public abstract class LocationChanger {
    private CoordinateSystem system;

    public LocationChanger(CoordinateSystem system) {
        this.system = system;
    }

    /**
     * 把坐标点转换为屏幕上的位置
     *
     * @param ps 坐标点
     * @return 屏幕上的位置
     */
    public PointDouble[] toReal(MyPoint... ps) {
        PointDouble[] pds = new PointDouble[ps.length];
        for (int i = 0; i < ps.length; i++) {
            pds[i] = toReal(ps[i]);
        }
        return pds;
    }

    public PointDouble[] toReal(List<MyPoint> ps) {
        PointDouble[] pds = new PointDouble[ps.size()];
        for (int i = 0; i < ps.size(); i++) {
            pds[i] = toReal(ps.get(i));
        }
        return pds;
    }

    public abstract PointDouble toReal(MyPoint p);

    /**
     * 把屏幕上的位置转换为坐标点
     *
     * @param ps 屏幕上的位置
     * @return 坐标点
     */
    public MyPoint[] toSystem(PointDouble... ps) {
        throw new UnsupportedOperationException("The toSystem has not supported by" + system.getClass().getSimpleName());
    }

    public MyPoint[] toSystem(List<PointDouble> ps) {
        return toSystem(ps.toArray(new PointDouble[0]));
    }

    public MyPoint toSystem(PointDouble p) {
        return toSystem(p, p)[0];
    }
}
