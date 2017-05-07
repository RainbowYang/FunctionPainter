package rainbow.inner.function.mathfunction.simple;

/**
 * 指数函数
 * f(x)=a*n<sup>x<</sup>
 *
 * @author Rainbow Yang
 */
public class ExpFunction extends SimpleMathFunction {
    private double times;
    private double base;

    public ExpFunction(double times, double base) {
        super();
        this.times = times;
        this.base = base;
        setFunction(x -> times * Math.pow(base, x));
    }

    @Override
    public String toString() {
        return "ExpFunction{f(x)=" + times + "*" + base + "^x";
    }
}
