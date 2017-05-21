package rainbow.inner.function.mathfunction.simple;

import rainbow.inner.function.MathFunction;

import java.util.function.Function;

/**
 * 简化版的MathFunction，对于第一个维度进行线性处理。
 *
 * @author Rainbow Yang
 */
public class SimpleMathFunction extends MathFunction {
    public SimpleMathFunction(Function<Double, Double> function) {
        super(x -> x, function);
    }

    public SimpleMathFunction() {
        super();
    }

    public void setFunction(Function<Double, Double> function) {
        setFunctions(x -> x, function);
    }
}
