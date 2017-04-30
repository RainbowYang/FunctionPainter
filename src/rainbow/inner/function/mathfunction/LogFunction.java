package rainbow.inner.function.mathfunction;

import rainbow.inner.function.MathFunction;

/**
 * 对数函数
 * f(x)=a*log<sub>n</sub>x
 *
 * @author Rainbow Yang
 */
public class LogFunction extends MathFunction {
    private double times;
    private double base;

    public LogFunction(double times, double base) {
        super();
        this.times = times;
        this.base = base;
        function = x -> times * (Math.log10(x) / Math.log10(base));
    }

    @Override
    public String toString() {
        return "LogFunction{f(x)" + times + "*log(" + base + ")x";
    }
}

