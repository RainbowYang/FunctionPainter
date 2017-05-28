package rainbow.inner.function.mathfunction.simple;

import rainbow.inner.function.mathfunction.MathFunction;

import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

/**
 * 简化版的MathFunction，对于第一个维度进行线性处理。
 *
 * @author Rainbow Yang
 */
public class SimpleMathFunction extends MathFunction {
    public SimpleMathFunction(DoubleUnaryOperator... functions) {
        setFunction(functions);
    }

    public SimpleMathFunction(List<DoubleUnaryOperator> functions) {
        setFunction(functions);
    }

    public void setFunction(DoubleUnaryOperator... functions) {
        setFunction(Arrays.asList(functions));
    }

    public void setFunction(List<DoubleUnaryOperator> functions) {
        functions.add(0, SELF);
        setFunctions(functions);
    }
}
