package rainbow.inner.function.mathfunction.simple._2D.spiral;

/**
 * 等角螺线、对数螺线或生长螺线是在自然界常见的螺线。
 * 在极坐标系(r, θ)中，这个曲线可以写为r = a *e^( b *theta)
 *
 * @author Rainbow Yang
 */
public class IsometricSpiral extends Spiral {
    //意义见类注释
    private double a = 1;
    private double b = 1;

    public IsometricSpiral() {
        setFunction(i -> a * Math.exp(b * i));
    }

    public IsometricSpiral(double a, double b) {
        setFunction(i -> a * Math.exp(b * i));
        this.a = a;
        this.b = b;
    }
}
