package rainbow.inner.function;

import rainbow.inner.coordinate.point.PointForAxes;
import rainbow.inner.coordinate.system.comp.Range;
import rainbow.inner.system.MySystem;

import java.util.function.Function;

/**
 * @author Rainbow Yang
 */
public class MathFunction extends PointFunction {
    protected Function<Double, Double> function;

    public MathFunction(Function<Double, Double> function) {
        super();
        this.function = function;
    }

    public MathFunction() {
        super();
    }

    {
        newPoints();
    }

    @Override
    public void calcPoint() {
        Range range = MySystem.getSystem().getCoordinateSystem().getRange();
        for (double i = range.getStart(); i < range.getEnd(); i += range.getStep()) {
            addPoint(new PointForAxes(i, function.apply(i)));
        }
    }

    public Function<Double, Double> getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return "MathFunction{" +
                "function=" + function +
                ", points=" + points +
                '}';
    }
}
