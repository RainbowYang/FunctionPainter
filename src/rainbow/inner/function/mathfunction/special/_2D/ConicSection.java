package rainbow.inner.function.mathfunction.special._2D;

import rainbow.inner.function.mathfunction.MathFunction;

/**
 * 圆锥曲线
 * <p>
 * 椭圆（Ellipse）
 * 椭圆上的点到两个焦点的距离和等于长轴长（2a）。
 * 抛物线（Parabola）
 * 抛物线上的点到焦点的距离等于该点到准线的距离。
 * 双曲线（Hyperbola）
 * 双曲线上的点到两个焦点的距离之差的绝对值等于贯轴长（2a）。
 *
 * @author Rainbow Yang
 */
public class ConicSection extends MathFunction {
    public static final String MODE_ELLIPSE_X = "Ellipse X";//椭圆,x轴为长轴,但允许让长轴比短轴短。
    public static final String MODE_ELLIPSE_Y = "Ellipse Y";//椭圆,y轴为长轴,但允许让长轴比短轴短。
    public static final String MODE_HYPERBOLA_X = "Hyperbola X";//双曲线,x轴为实轴
    public static final String MODE_HYPERBOLA_Y = "Hyperbola Y";//双曲线,y轴为虚轴
    public static final String MODE_PARABOLA_X = "Parabola X";//抛物线,x轴为对称轴
    public static final String MODE_PARABOLA_Y = "Parabola Y";//抛物线,y轴为对称轴

    private double a;
    private double b;
    private double p;

    public ConicSection(double a, double b, String mode) {

        //todo 暂时
        setStart(-Math.PI);
        setEnd(Math.PI);
        setStep(0.0001);

        switch (mode) {
            case MODE_ELLIPSE_X:
                setFunctions(i -> a * Math.sin(i), i -> b * Math.cos(i));
                return;
            case MODE_ELLIPSE_Y:
                setFunctions(i -> b * Math.cos(i), i -> a * Math.sin(i));
                return;
            case MODE_HYPERBOLA_X:
                setFunctions(i -> a / Math.sin(i), i -> b / Math.tan(i));
                return;
            case MODE_HYPERBOLA_Y:
                setFunctions(i -> b / Math.tan(i), i -> a / Math.sin(i));
                return;
        }
        throw new IllegalArgumentException(mode + "is not allowed to use in ConicSection.");
    }

    public ConicSection(double p, String mode) {
        switch (mode) {
            case MODE_PARABOLA_X:
                setFunctions(i -> (i * i) / (2 * p), i -> i);
                return;
            case MODE_PARABOLA_Y:
                setFunctions(i -> i, i -> (i * i) / (2 * p));
                return;
        }
    }

}
