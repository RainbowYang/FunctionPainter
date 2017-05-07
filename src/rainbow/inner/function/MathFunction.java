package rainbow.inner.function;

import rainbow.inner.coordinate.point.PointForAxes;
import rainbow.inner.coordinate.system.comp.Range;
import rainbow.inner.system.MySystem;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * 本类由各个维度上的Function来控制。
 * 并自动根据取值范围生成点。
 *
 * @author Rainbow Yang
 */
public class MathFunction extends PointFunction {
    protected List<Function<Double, Double>> functions;
    private double start, end, step;

    public MathFunction(List<Function<Double, Double>> functions) {
        super();
        this.functions = functions;

    }

    public MathFunction(Function<Double, Double>... functions) {
        super();
        this.functions = Arrays.asList(functions);
    }

    public MathFunction() {
        super();
    }

    {
        newPoints();

        Range range = MySystem.getSystem().getCoordinateSystem().getRange();
        start = range.getStart();
        end = range.getEnd();
        step = range.getStep();
    }

    @Override
    public void calcPoint() {
        for (double i = start; i < end; i += step) {
            double[] ds = new double[functions.size()];
            for (int j = 0; j < ds.length; j++) {
                ds[j] = functions.get(j).apply(i);
            }
            addPoint(new PointForAxes(ds));
        }
    }

    public void setFunctions(List<Function<Double, Double>> functions) {
        this.functions = functions;
    }
    public void setFunctions(Function<Double, Double>... functions) {
        this.functions = Arrays.asList(functions);
    }

    public void setStart(double start) {
        this.start = start;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public List<Function<Double, Double>> getFunctions() {
        return functions;
    }

    @Override
    public String toString() {
        return "MathFunction{" +
                "functions=" + functions +
                ", points=" + points +
                '}';
    }
}
