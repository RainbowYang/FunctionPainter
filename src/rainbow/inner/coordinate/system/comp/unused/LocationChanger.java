package rainbow.inner.coordinate.system.comp.unused;

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
public abstract class LocationChanger implements CoordinateSystemComponent {
    private CoordinateSystem cs;

    public LocationChanger(CoordinateSystem cs) {
        this.cs = cs;
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
        MyPoint[] pds = new MyPoint[ps.length];
        for (int i = 0; i < ps.length; i++) {
            pds[i] = toSystem(ps[i]);
        }
        return pds;
    }

    public MyPoint[] toSystem(List<PointDouble> ps) {
        MyPoint[] pds = new MyPoint[ps.size()];
        for (int i = 0; i < ps.size(); i++) {
            pds[i] = toSystem(ps.get(i));
        }
        return pds;
    }

    public MyPoint toSystem(PointDouble p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getKeyName() {
        return staticGetKeyName();
    }

    public static String staticGetKeyName() {
        return "LocationChanger";
    }
}
