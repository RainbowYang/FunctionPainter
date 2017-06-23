package rainbow.inner.function.mathfunction.special;

import rainbow.inner.function.mathfunction.MathFunction;

import java.util.function.DoubleBinaryOperator;

/**
 * 我实在不知道这个要叫什么好了 0.0.
 * <p>
 * 通过一个 f(a,b,c..) 的多元函数
 * 如果多元函数有n个参数
 * 那么就在前n个维度上构建网格（并不就是二维），三维的类似于脚手夹
 *
 * @author Rainbow Yang
 */
public class Net extends MathFunction {

    {
        setRange(-10, 10, 0.1);
    }

    public Net(DoubleBinaryOperator function) {
        for (int j = (int) start; j < (int) end; j++) {
            final int[] js = {j};
            addFunctions(i -> i, i -> js[0], i -> function.applyAsDouble(i, js[0]));
            addFunctions(i -> js[0], i -> i, i -> function.applyAsDouble(js[0], i));
        }
    }

    public Net(DoubleTernaryOperator function) {
        for (int j = (int) start; j < (int) end; j += 10) {
            for (int k = (int) start; k < (int) end; k++) {
                final int[] js = {j, k};
                addFunctions(SELF, i -> js[0], i -> js[1], i -> function.applyAsDouble(i, js[0], js[1]));
                addFunctions(SELF, i -> js[1], i -> js[0], i -> function.applyAsDouble(i, js[1], js[0]));
                addFunctions(i -> js[0], SELF, i -> js[1], i -> function.applyAsDouble(js[0], i, js[1]));
                addFunctions(i -> js[1], SELF, i -> js[0], i -> function.applyAsDouble(js[1], i, js[0]));
                addFunctions(i -> js[0], i -> js[1], SELF, i -> function.applyAsDouble(js[0], js[1], i));
                addFunctions(i -> js[1], i -> js[0], SELF, i -> function.applyAsDouble(js[1], js[0], i));
            }
        }
    }

    @FunctionalInterface
    public interface DoubleTernaryOperator {
        public double applyAsDouble(double d1, double d2, double d3);
    }
}
