package rainbow.inner.math;

import static java.lang.Math.*;

/**
 * 我的数学类，实现一些Math没有的功能。
 *
 * @author Rainbow Yang
 */
public class MyMath {
    /**
     * 进行四舍五入
     *
     * @param n 需要进行四舍五入的Number
     * @return 进行四舍五入后的int
     */
    public static int toInt(Number n) {
        return (int) (n.intValue() + 0.5);
    }

    /**
     * 特殊的开平方
     * 当其小于0的，返回其绝对值的算数平方根的负值。
     * <p>
     * specialSqrt(-a)=-sqrt(a)
     *
     * @param a 需要开平方的数
     * @return 平方根
     */
    public static double specialSqrt(double a) {
        return a >= 0 ? sqrt(a) : sqrt(-a);
    }
}
