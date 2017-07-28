package rainbow.function.mathfunction.simple._2D.spiral;

import static java.lang.Math.sqrt;

/**
 * 连锁螺线(Lituus spiral) r^2*theta =k
 * Lituus 螺线 是所有形式为 r^2*theta=k 的螺线。
 *
 * @author Rainbow Yang
 */
public class LituusSpiral extends Spiral {
    private double k = 1;

    public LituusSpiral(double k) {
        setFunction(i -> sqrt(k / i));
        setFunction(i -> -sqrt(k / i));
        this.k = k;
    }
}
