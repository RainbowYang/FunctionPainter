package rainbow.inner.coordinate.system.comp.unused;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 用于管理函数的取值。
 *
 * @author Rainbow Yang
 */
public class Values {

    /**
     * 线性增长
     */
    public static final Function<Double, Double> LINEAR = x -> x;
    public static final Function<Double, Double> XIAN_XING = LINEAR;
    /**
     * 指数增长
     */
    public static final BiFunction<Double, Double, Double> EXPONENTIAL = (x, i) -> Math.pow(x, i);
    public static final BiFunction<Double, Double, Double> ZHI_SHU = EXPONENTIAL;
    /**
     * 对数增长
     */
    public static final BiFunction<Double, Double, Double> LOGARITHML = (x, i) -> Math.log10(i) / Math.log10(x);
    public static final BiFunction<Double, Double, Double> DUI_SHU = LOGARITHML;

    private double start;
    private double end;
    private double step;
    private Function<Double, Double> function;
    private BiFunction<Double, Double, Double> biFunction;
    private double base;//指数和对数的基数

    private Double[] doubles;

    public Values(double start, double end, double step) {
        this.start = start;
        this.end = end;
        this.step = step;
    }

    public Values(double start, double end, double step, Function<Double, Double> function) {
        this.start = start;
        this.end = end;
        this.step = step;
        this.function = function;
    }

    public Values(double start, double end, double step, BiFunction<Double, Double, Double> biFunction, double base) {
        this.start = start;
        this.end = end;
        this.step = step;
        this.biFunction = biFunction;
        this.base = base;
    }

    public Double[] getValues() {
        if (doubles == null) {
            ArrayList<Double> ds = new ArrayList<>();
            double d = 0;
            for (double i = start; i < end; i += step) {
                if (biFunction != null) {
                    d = biFunction.apply(base, i);
                } else if (function != null) {
                    d = function.apply(i);
                }
                ds.add(d);
            }
            doubles = new Double[ds.size()];
            return ds.toArray(doubles);
        } else {
            return doubles;
        }
    }
}
