package rainbow.inner.function.mathfunction.special._2D.cycloid;

import rainbow.inner.function.mathfunction.MathFunction;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * 内旋轮线（英语：hypotrochoid）是追踪附着在围绕半径为 R 的固定的圆内侧滚转的半径为 r 的圆上的一个点得到的转迹线，这个点到内部滚动的圆的中心的距离是 d。
 * 内旋轮线的参数方程是:
 * x = (R-r)*cos(θ)+d*cos(((R-r)/r)*θ)
 * y = (R-r)*sin(θ)-d*sin(((R-r)/r)*θ)
 *
 * @author Rainbow Yang
 */
public class Hypotrochoid extends MathFunction {
    private double R = 5, r = 3, d = 5;

    public Hypotrochoid(double R, double r, double d) {
        setFunctions(θ -> (R - r) * cos(θ) + d * cos(((R - r) / r) * θ),
                θ -> (R - r) * sin(θ) - d * sin(((R - r) / r) * θ));
        this.R = R;
        this.r = r;
        this.d = d;
    }

    public Hypotrochoid() {
        setFunctions(θ -> (R - r) * cos(θ) + d * cos(((R - r) / r) * θ),
                θ -> (R - r) * sin(θ) - d * sin(((R - r) / r) * θ));
    }
}
