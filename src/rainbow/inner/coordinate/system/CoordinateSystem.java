package rainbow.inner.coordinate.system;

import rainbow.inner.coordinate.system.comp.Colors;
import rainbow.inner.coordinate.system.comp.Values;


/**
 * @author Rainbow Yang
 * @date 2017/4/4
 */
public abstract class CoordinateSystem {
    protected Colors colors;
    protected Values values;

    public Colors getColors() {
        return colors;
    }

    public Values getValues() {
        return values;
    }

    public abstract void setChanged();
}
