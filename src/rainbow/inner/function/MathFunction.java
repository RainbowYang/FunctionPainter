package rainbow.inner.function;

import rainbow.inner.coordinate.point.PointForAxes;
import rainbow.inner.coordinate.system.comp.Range;
import rainbow.inner.system.MySystem;

import java.util.function.Function;

/**
 * @author Rainbow Yang
 */
public class MathFunction extends PointFunction {
    private Function<Double, Double> function;

    public MathFunction(Function<Double, Double> function) {
        this.function = function;
    }

    {
        newPoints();
    }

    //todo 范围未定
    public void calcPoint() {
        Range range = MySystem.getSystem().getCoordinateSystem().getRange();
        for (double i = range.getStart(); i < range.getEnd(); i += range.getStep()) {
            addPoint(new PointForAxes(i, function.apply(i)));
        }
    }

    @Override
    public String toString() {
        return "MathFunction{" +
                "function=" + function +
                ", points=" + points +
                '}';
    }
}
