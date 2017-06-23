package rainbow.inner.function.mathfunction.special._2D.cycloid;

import rainbow.inner.function.mathfunction.MathFunction;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * 外旋轮线（Epitrochoid）是追踪附着在围绕半径为R的固定的圆外侧滚转的半径r的圆上的一个点而得到的转迹线，这个点距离外部滚动的圆的中心的距离是d。
 * 外旋轮线的参数方程是:
 * x = (R+r)*cos(θ)-d*cos(((R+r)/r)*θ)
 * y = (R+r)*sin(θ)-d*sin(((R+r)/r)*θ)
 *
 * @author Rainbow Yang
 */
public class Epitrochoid extends MathFunction {
    private double R = 5, r = 3, d = 5;

    public Epitrochoid(double R, double r, double d) {
        setFunctions(θ -> (R + r) * cos(θ) - d * cos(((R + r) / r) * θ),
                θ -> (R + r) * sin(θ) - d * sin(((R + r) / r) * θ));
        this.R = R;
        this.r = r;
        this.d = d;
    }

    public Epitrochoid() {
        setFunctions(θ -> (R + r) * cos(θ) - d * cos(((R + r) / r) * θ),
                θ -> (R + r) * sin(θ) - d * sin(((R + r) / r) * θ));
    }
}
