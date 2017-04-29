package rainbow.inner.coordinate.system;

import rainbow.inner.coordinate.system.comp.LocationChanger;

/**
 * 坐标系
 * 主要负责坐标的换算
 *
 * @author Rainbow Yang
 */
public abstract class CoordinateSystem {
    protected LocationChanger changer;
    protected double x, y;

    public CoordinateSystem() {
        initLocationChanger();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public LocationChanger getLocationChanger() {
        return changer;
    }

    protected abstract void initLocationChanger();

}
