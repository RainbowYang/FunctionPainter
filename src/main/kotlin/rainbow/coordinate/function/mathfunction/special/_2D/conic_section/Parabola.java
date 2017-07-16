package rainbow.coordinate.function.mathfunction.special._2D.conic_section;

import rainbow.coordinate.function.mathfunction.MathFunction;

/**
 * @author Rainbow Yang
 */
public class Parabola extends ConicSection {
    private double p;

    public Parabola(double p, boolean towards) {
        this.p = p;

        if (towards)
            setFunctions(i -> (i * i) / (2 * p), MathFunction.Companion.getSELF());
        else
            setFunctions(i -> i, i -> (i * i) / (2 * p));
    }
}
