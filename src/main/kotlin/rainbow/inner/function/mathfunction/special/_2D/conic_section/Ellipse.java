package rainbow.inner.function.mathfunction.special._2D.conic_section;

/**
 * @author Rainbow Yang
 */
public class Ellipse extends ConicSection {
    private double a, b;

    public Ellipse(double a, double b, boolean towards) {
        this.a = a;
        this.b = b;

        if (towards)
            setFunctions(i -> a * Math.sin(i), i -> b * Math.cos(i));
        else
            setFunctions(i -> b * Math.sin(i), i -> a * Math.cos(i));
    }
}
