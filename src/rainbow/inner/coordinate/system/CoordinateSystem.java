package rainbow.inner.coordinate.system;

import rainbow.inner.coordinate.system.comp.LocationCalculator;
import rainbow.inner.coordinate.system.comp.Values;


/**
 * @author Rainbow Yang
 * @date 2017/4/4
 */
public abstract class CoordinateSystem {
    protected Values values;
    protected LocationCalculator location;

    public CoordinateSystem() {
        initValues();
        initLocationCalculator();
    }

    protected abstract void initValues();

    protected abstract void initLocationCalculator();

    public abstract void setChanged();

    public LocationCalculator getLocationCalculator() {
        return location;
    }

    public Values getValues() {
        return values;
    }

}
