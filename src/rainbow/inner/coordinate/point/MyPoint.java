package rainbow.inner.coordinate.point;

/**
 * 这里是所有的点的接口,定义一些可能用的方法.
 * 所有方法默认给出UnsupportedOperationException
 *
 * @author Rainbow Yang
 * @see UnsupportedOperationException
 */
public interface MyPoint {

    PointForAxes toPointForAxes();

    /**
     * 用于两个点的叠加
     *
     * @param pt 需要相加的点
     * @return 两者相加之后的点
     */
    public default MyPoint add(MyPoint pt) {
        throw new UnsupportedOperationException(getDescription("Add"));
    }

    /**
     * 用于两个点的相减
     * 也就是调用方法的点相对于被当做参数的点的位置
     *
     * @param pt 需要相减的点
     * @return 两者相减之后的点
     */
    public default MyPoint reduce(MyPoint pt) {
        throw new UnsupportedOperationException(getDescription("Add"));
    }

    /**
     * 将一个点进行缩放
     *
     * @param times 需要缩放的倍数
     * @return 缩放之后的点
     */
    public default MyPoint times(double times) {
        throw new UnsupportedOperationException(getDescription("Times"));
    }

    default String getDescription(String name) {
        return new StringBuilder().append(name).append("function hasn't been supported by")
                .append(this.getClass().getSimpleName()).toString();
    }
}
