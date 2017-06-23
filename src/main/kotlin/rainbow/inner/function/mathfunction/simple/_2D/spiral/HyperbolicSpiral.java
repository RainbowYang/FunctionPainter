package rainbow.inner.function.mathfunction.simple._2D.spiral;

/**
 * 双曲螺线（Hyperbolic spiral）又称倒数螺线（reciprocal spiral）。
 * 轨迹定义
 * 极径与极角成反比的点的轨迹称为双曲螺线。
 * <p>
 * r = c\theta
 *
 * @author Rainbow Yang
 */
public class HyperbolicSpiral extends Spiral {
    private double c = 5;

    public HyperbolicSpiral(double c) {
        setFunction(i -> c / i);
        this.c = c;
    }

    public HyperbolicSpiral() {
        setFunction(i -> c / i);
    }

    {
        setStart(0);
    }

}
