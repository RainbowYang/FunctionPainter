package rainbow.inner.point;

/**
 * 这里是所有的点的借口,定义一些可能用的方法.
 * 所有方法默认给出UnsupportedOperationException
 *
 * @author Rainbow Yang
 * @date 2017/4/2
 * @see UnsupportedOperationException
 */
public interface MyPoint<P extends MyPoint> {

    /**
     * 用于两个点的叠加
     *
     * @param pt 需要相加的点
     * @return 两者相加之后的点
     */
    public default P add(P pt) {
        throw new UnsupportedOperationException(getDescription("Add"));
    }

    /**
     * 用于两个点的相减
     * 也就是调用方法的点相对于被当做参数的点的位置
     *
     * @param pt 需要相减的点
     * @return 两者相减之后的点
     */
    public default P reduce(P pt) {
        throw new UnsupportedOperationException(getDescription("Add"));
    }

    /**
     * 将一个点[逆时针]绕原点旋转一定角度
     *
     * @param angle 需要旋转的角度(单位为弧度)
     * @return 旋转之后得到的点
     */
    public default P spin(double angle) {
        throw new UnsupportedOperationException(getDescription("Spin"));
    }

    /**
     * 将一个点[逆时针]绕所给定的点旋转一定角度
     *
     * @param center 旋转中心
     * @param angle  需要旋转的角度(单位为弧度)
     * @return 旋转之后得到的点
     */
    public default P spin(P center, double angle) {
        throw new UnsupportedOperationException(getDescription("Spin"));
    }

    /**
     * 将一个点进行缩放
     *
     * @param times 需要缩放的倍数
     * @return 缩放之后的点
     */
    public default P times(double times) {
        throw new UnsupportedOperationException(getDescription("Times"));
    }

    default String getDescription(String name) {
        return new StringBuilder().append(name).append("function hasn't been supported by")
                .append(this.getClass().getSimpleName()).toString();
    }
}
