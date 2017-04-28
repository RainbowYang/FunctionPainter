package rainbow.inner.coordinate.system;

import rainbow.inner.coordinate.system.comp.LocationChanger;
import rainbow.inner.coordinate.system.comp.Values;


/**
 * @author Rainbow Yang
 */
public abstract class CoordinateSystem {
    protected Values values;
    protected LocationChanger location;

    public CoordinateSystem() {
        initValues();
        initLocationCalculator();
    }

    protected abstract void initValues();

    protected abstract void initLocationCalculator();

    public abstract void setChanged();

    public LocationChanger getLocationCalculator() {
        return location;
    }

    public Values getValues() {
        return values;
    }

}
