package rainbow.inner.function.mathfunction.special._2D.cycloid;

/**
 * 内摆线（英语：hypocycloid）是追踪附着在围绕半径为 R 的固定的圆内侧滚转的半径为 r 的圆上的一个点得到的转迹线，这个点到内部滚动的圆的中心的距离是r。
 * 内旋轮线的参数方程是:
 * x = (R-r)*cos(θ)+r*cos(((R-r)/r)*θ)
 * y = (R-r)*sin(θ)-r*sin(((R-r)/r)*θ)
 *
 * @author Rainbow Yang
 */
public class Hypocycloid extends Hypotrochoid {
    public Hypocycloid(double R, double r) {
        super(R, r, r);
    }
}
