package rainbow.inner.math;

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
    public int toInt(Number n) {
        return (int) (n.intValue() + 0.5);
    }
}
