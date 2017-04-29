package rainbow.inner.function;

import rainbow.inner.coordinate.point.PointForAxes;

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
        for (double i = 0; i < 10; i++) {
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
