package rainbow.inner.function.mathfunction.special._2D.conic_section;

/**
 * @author Rainbow Yang
 */
public class Parabola extends ConicSection {
    private double p;

    public Parabola(double p, boolean towards) {
        this.p = p;

        if (towards)
            setFunctions(i -> (i * i) / (2 * p), SELF);
        else
            setFunctions(i -> i, i -> (i * i) / (2 * p));
    }
}
