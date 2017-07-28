package rainbow.function.mathfunction.special._2D.conic_section;

/**
 * @author Rainbow Yang
 */
public class Hyperbola extends ConicSection {
    private double a, b;

    public Hyperbola(double a, double b, boolean towards) {
        this.a = a;
        this.b = b;

        if (towards)
            setFunctions(i -> a / Math.sin(i), i -> b / Math.tan(i));
        else
            setFunctions(i -> b / Math.tan(i), i -> a / Math.sin(i));
    }
}
