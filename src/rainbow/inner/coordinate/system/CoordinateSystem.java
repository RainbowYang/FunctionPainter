package rainbow.inner.coordinate.system;

import rainbow.inner.coordinate.system.comp.Colors;
import rainbow.inner.coordinate.system.comp.Functions;
import rainbow.inner.coordinate.system.comp.Values;


/**
 * @author Rainbow Yang
 * @date 2017/4/4
 */
public abstract class CoordinateSystem {
    protected Colors colors;
    protected Values values;
    protected Functions functions;

    public CoordinateSystem() {
        colors = new Colors();
        functions = new Functions();
        initValues();
    }

    protected abstract void initValues();

    public abstract void setChanged();

    public Colors getColors() {
        return colors;
    }

    public Values getValues() {
        return values;
    }

    public Functions getFunctions() {
        return functions;
    }
}
