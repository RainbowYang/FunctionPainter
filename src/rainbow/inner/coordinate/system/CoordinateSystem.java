package rainbow.inner.coordinate.system;

import rainbow.inner.coordinate.system.comp.Listeners;
import rainbow.inner.coordinate.system.comp.LocationChanger;
import rainbow.inner.coordinate.system.comp.Range;
import rainbow.inner.coordinate.system.comp.SystemPainter;
import rainbow.inner.system.MySystem;
import rainbow.inner.system.SystemComponent;

/**
 * 坐标系
 * 主要负责坐标的换算
 *
 * @author Rainbow Yang
 */
public abstract class CoordinateSystem implements SystemComponent {
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

    {
        x = MySystem.getSystem().getWidth() / 2;
        y = MySystem.getSystem().getHeight() / 2;
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

    public void setAngles(double[] angles) {
    }

    public void setAngle(int index, double angle) {
    }

    public void setLengths(double[] lengths) {
    }

    public void setLength(int index, double length) {
    }

    public void setLengths(double times) {
    }

    @Override
    public String getKeyName() {
        return staticGetKeyName();
    }

    public static String staticGetKeyName() {
        return "CoordinateSystem";
    }
}
