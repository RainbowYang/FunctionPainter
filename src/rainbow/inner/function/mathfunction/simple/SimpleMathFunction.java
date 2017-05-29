package rainbow.inner.function.mathfunction.simple;

import rainbow.inner.function.mathfunction.MathFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

/**
 * 简化版的MathFunction，对于任意一个维度进行线性处理。默认为第一个。
 *
 * @author Rainbow Yang
 */
public class SimpleMathFunction extends MathFunction {

    public SimpleMathFunction() {
    }

    public SimpleMathFunction(DoubleUnaryOperator... functions) {
        setFunction(0, functions);
    }

    public SimpleMathFunction(List<DoubleUnaryOperator> functions) {
        setFunction(0, functions);
    }

    public SimpleMathFunction(int index, DoubleUnaryOperator... functions) {
        setFunction(index, functions);
    }

    public SimpleMathFunction(int index, List<DoubleUnaryOperator> functions) {
        setFunction(index, functions);
    }

    public void setFunction(DoubleUnaryOperator... functions) {
        setFunction(0, functions);
    }

    public void setFunction(List<DoubleUnaryOperator> functions) {
        setFunction(0, functions);
    }

    public void setFunction(int index, DoubleUnaryOperator... functions) {
        setFunction(index, Arrays.asList(functions));
    }

    public void setFunction(int index, List<DoubleUnaryOperator> functions) {
        ArrayList<DoubleUnaryOperator> newFunctions = new ArrayList<>();
        newFunctions.addAll(functions);
        newFunctions.add(index, SELF);
        //todo
        addFunctions(newFunctions);
    }
}
