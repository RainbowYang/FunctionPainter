package rainbow.inner.function.mathfunction.special._2D.conic_section;

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
public abstract class ConicSection extends MathFunction {
    //对于椭圆，x轴为长轴；对于双曲线,x轴为实轴；对于抛物线,x轴为对称轴
    public static final boolean X_TOWARDS = true;
    //对于椭圆，y轴为长轴；对于双曲线,y轴为实轴；对于抛物线,y轴为对称轴
    public static final boolean Y_TOWARDS = false;

    {
        //todo 暂时
        setStart(-Math.PI);
        setEnd(Math.PI);
        setStep(0.0001);
    }

}
