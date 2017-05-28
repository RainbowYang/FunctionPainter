package rainbow.inner.function.mathfunction.simple._2D;

import rainbow.inner.function.mathfunction.simple.SimpleMathFunction;

/**
 * 对数函数
 * f(x)=a*log<sub>n</sub>x
 *
 * @author Rainbow Yang
 */
public class LogFunction extends SimpleMathFunction {
    private double times;
    private double base;

    public LogFunction(double times, double base) {
        super();
        this.times = times;
        this.base = base;

        setFunction(x -> times * (Math.log10(x) / Math.log10(base)));
    }

    @Override
    public String toString() {
        return "LogFunction{f(x)=" + times + "*log(" + base + ")x";
    }
}

