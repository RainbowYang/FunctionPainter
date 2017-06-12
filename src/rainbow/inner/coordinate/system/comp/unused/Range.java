package rainbow.inner.coordinate.system.comp.unused;

import rainbow.inner.coordinate.system.CoordinateSystem;

/**
 * 范围
 *
 * @author Rainbow Yang
 */
public class Range implements CoordinateSystemComponent {
    private CoordinateSystem cs;

    public Range(CoordinateSystem cs) {
        this.cs = cs;
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
