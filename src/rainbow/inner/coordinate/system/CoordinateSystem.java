package rainbow.inner.coordinate.system;

import rainbow.inner.coordinate.system.comp.*;

/**
 * 坐标系
 * 主要负责坐标的换算
 *
 * @author Rainbow Yang
 */
public abstract class CoordinateSystem {
    protected LocationChanger changer;
    protected SystemPainter painter;
    protected Range range;
    protected Listeners listeners;

    protected double x, y;

    CoordinateSystem() {
        initLocationChanger();
        initSystemPainter();
        initRange();
        initListeners();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public LocationChanger getLocationChanger() {
        return changer;
    }

    public SystemPainter getPainter() {
        return painter;
    }

    public Range getRange() {
        return range;
    }

    public Listeners getListeners() {
        return listeners;
    }

    protected abstract void initLocationChanger();

    protected abstract void initSystemPainter();

    protected abstract void initRange();

    protected abstract void initListeners();

}
