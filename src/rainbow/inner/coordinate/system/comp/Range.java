package rainbow.inner.coordinate.system.comp;

import rainbow.inner.coordinate.system.CoordinateSystem;

/**
 * 范围
 *
 * @author Rainbow Yang
 */
public class Range implements CoordinateSystemComponent {
    private CoordinateSystem system;

    public Range(CoordinateSystem system) {
        this.system = system;
    }

    public double getStart() {
        return -20;
    }

    public double getStep() {
        return 0.01;
    }

    public double getEnd() {
        return 20;
    }

    @Override
    public String getKeyName() {
        return staticGetKeyName();
    }

    public static String staticGetKeyName() {
        return "Range";
    }

}
