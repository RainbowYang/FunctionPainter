package rainbow.inner.coordinate.system.comp;

import rainbow.inner.coordinate.system.CoordinateSystem;

/**
 * 范围
 *
 * @author Rainbow Yang
 */
public abstract class Range {
    private CoordinateSystem system;

    public Range(CoordinateSystem system) {
        this.system = system;
    }

    public abstract double getStart();

    public abstract double getStep();

    public abstract double getEnd();

}
