package rainbow.coordinate.function.mathfunction.simple._3D;

import rainbow.coordinate.function.mathfunction.simple.SimpleMathFunction;

import static java.lang.Math.*;


/**
 * 三维椭球
 *
 * @author Rainbow Yang
 */
public class Ellipsoid extends SimpleMathFunction {
    //三个方向的长度
    private double a, b, c;

    //密集程度
    private double denseness = 30;

    public Ellipsoid() {
        this(10, 10, 10);
    }

    public Ellipsoid(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;

        // setFunction(0,
        //         i -> a * sqrt(1 - (i / c) * (i / c)) * sin((i / c) * PI * denseness),
        //         i -> b * sqrt(1 - (i / c) * (i / c)) * cos((i / c) * PI * denseness)
        // );
        // setFunction(1,
        //         i -> a * sqrt(1 - (i / c) * (i / c)) * sin((i / c) * PI * denseness),
        //         i -> b * sqrt(1 - (i / c) * (i / c)) * cos((i / c) * PI * denseness)
        // );
        setFunction(2,
                i -> a * sqrt(1 - (i / c) * (i / c)) * sin((i / c) * PI * denseness),
                i -> b * sqrt(1 - (i / c) * (i / c)) * cos((i / c) * PI * denseness)
        );

        setStart(-c);
        setEnd(c);
        setStep(0.001);
    }

    public Ellipsoid(double r) {
        this(r, r, r);
    }
}
