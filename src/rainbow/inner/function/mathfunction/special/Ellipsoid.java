package rainbow.inner.function.mathfunction.special;

import rainbow.inner.function.mathfunction.MathFunction;

import static java.lang.Math.*;

/**
 * 三维椭球
 *
 * @author Rainbow Yang
 */
public class Ellipsoid extends MathFunction {
    //三个方向的长度
    private double a, b, c;

    //密集程度
    private double denseness = 100;

    public Ellipsoid() {
        this(10, 10, 10);
    }

    public Ellipsoid(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;

        setFunctions(i -> a * sqrt(1 - i * i) * sin(i * PI * denseness),
                i -> b * sqrt(1 - i * i) * cos(i * PI * denseness), i -> c * i);

    }

    public Ellipsoid(double r) {
        this(r, r, r);
    }

    {
        setStart(-1);
        setEnd(1);
        setStep(0.001);
    }
}
