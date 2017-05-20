package rainbow.inner.coordinate.system;

import rainbow.inner.coordinate.system.comp.*;
import rainbow.inner.scalable.ComponentScalable;
import rainbow.inner.system.MySystem;
import rainbow.inner.system.SystemComponent;

/**
 * 坐标系
 * 主要负责坐标的换算
 *
 * @author Rainbow Yang
 */
public abstract class CoordinateSystem extends ComponentScalable<CoordinateSystemComponent> implements SystemComponent {
    private double x, y;

    public CoordinateSystem() {
        setX(MySystem.getWidth() / 2);
        setY(MySystem.getHeight() / 2);

        setComp(new Axes(this));
        setComp(new Mover(this));
        setComp(new Range(this));

        setComp(new EventListener(this));
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

    public Axes getAxes() {
        return (Axes) getComp(Axes.staticGetKeyName());
    }

    public Mover getMover() {
        return (Mover) getComp(Mover.staticGetKeyName());
    }

    public Range getRange() {
        return (Range) getComp(Range.staticGetKeyName());
    }

    public LocationChanger getLocationChanger() {
        return (LocationChanger) getComp(LocationChanger.staticGetKeyName());
    }

    public SystemPainter getPainter() {
        return (SystemPainter) getComp(SystemPainter.staticGetKeyName());
    }

    public EventListener getEventListener() {
        return (EventListener) getComp(EventListener.staticGetKeyName());
    }

    @Override
    public String getKeyName() {
        return staticGetKeyName();
    }

    public static String staticGetKeyName() {
        return "CoordinateSystem";
    }


}
