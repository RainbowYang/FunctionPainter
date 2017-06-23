package rainbow.inner.function.mathfunction.special._2D.cycloid;

import rainbow.inner.function.mathfunction.MathFunction;

/**
 * 在数学中，摆线（Cycloid）被定义为，一个圆沿一条直线运动时，圆边界上一定点所形成的轨迹。它是一般旋轮线的一种。(Wiki)
 * <p>
 * x=r*(t-sin(t))
 * y=r*(1-sin(t))
 *
 * @author Rainbow Yang
 */
public class Cycloid extends MathFunction {
    private double r = 2;

    public Cycloid(double r) {
        setFunctions(i -> r * (i - Math.sin(i)), i -> r * (1 - Math.cos(i)));
        this.r = r;
    }

    public Cycloid() {
        setFunctions(i -> r * (i - Math.sin(i)), i -> r * (1 - Math.cos(i)));
    }
}
