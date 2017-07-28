package rainbow.function.mathfunction.special._2D.cycloid;

/**
 * 外摆线（Epitrochoid）是追踪附着在围绕半径为R的固定的圆外侧滚转的半径r的圆上的一个点而得到的转迹线，这个点距离外部滚动的圆的中心的距离是r。
 * 外摆线的参数方程是:
 * x = (R+r)*cos(θ)-r*cos(((R+r)/r)*θ)
 * y = (R+r)*sin(θ)-r*sin(((R+r)/r)*θ)
 *
 * @author Rainbow Yang
 */
public class Epicycloid extends Epitrochoid {
    public Epicycloid(double R, double r) {
        super(R, r, r);
    }

    // public Epicycloid(int k, double r) {
    //     super(k * r, r, r);
    // }
}
