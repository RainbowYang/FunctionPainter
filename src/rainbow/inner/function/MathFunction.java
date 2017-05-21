package rainbow.inner.function;

import rainbow.inner.coordinate.point.PointForAxes;
import rainbow.inner.coordinate.system.comp.Range;
import rainbow.inner.system.MySystem;

import java.util.ArrayList;
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
    private List<
            //每组Function都会生成一个点集
            List<Function<Double, Double>>
            > functions = new ArrayList<>();
    private double start, end, step;

    //每个点的偏移，可用于平移
    private List<Double> startPlace;

    public MathFunction(Function<Double, Double>... functions) {
        super();
        this.functions = new ArrayList<>();
        this.functions.add(Arrays.asList(functions));
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

    //得到所有的点
    @Override
    public void calcPoint() {
        for (double i = start; i < end; i += step) {
            for (List<Function<Double, Double>> functions : this.functions) {
                double[] ds = new double[functions.size()];
                for (int j = 0; j < ds.length; j++) {
                    ds[j] = functions.get(j).apply(i) + getStartPlace(j);
                }
                addPoint(new PointForAxes(ds));
            }
        }
    }

    private double getStartPlace(int index) {
        if (startPlace == null || index >= startPlace.size()) {
            return 0;
        }
        return startPlace.get(index);
    }

    protected void setStartPlace(Double... startPlace) {
        this.startPlace = Arrays.asList(startPlace);
    }

    protected void setFunctions(List<Function<Double, Double>> functions) {
        this.functions.add(functions);
    }

    protected void setFunctions(Function<Double, Double>... functions) {
        setFunctions(Arrays.asList(functions));
    }

    protected void setStart(double start) {
        this.start = start;
    }

    protected void setEnd(double end) {
        this.end = end;
    }

    protected void setStep(double step) {
        this.step = step;
    }

    protected void setRange(double start, double step, double end) {
        setStart(start);
        setStep(step);
        setEnd(end);
    }


    public List<List<Function<Double, Double>>> getFunctions() {
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
