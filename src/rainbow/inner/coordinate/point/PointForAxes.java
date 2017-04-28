package rainbow.inner.coordinate.point;

/**
 * 轴坐标点
 * <p>
 * 用数组实现任意维度
 * 本类不可变，所有修改操作均返回重新产生的点
 *
 * @author Rainbow Yang
 */
public class PointForAxes implements MyPoint<PointForAxes> {
    private double[] values;

    public PointForAxes(double... values) {
        this.values = values;
    }

    @Override
    public PointForAxes add(PointForAxes pt) {
        int size = this.getBigger(pt).size();
        double[] ds = new double[size];
        for (int i = 0; i < size; i++) {
            ds[i] = (this.get(i) + pt.get(i));
        }
        return new PointForAxes(ds);
    }

    @Override
    public PointForAxes reduce(PointForAxes pt) {
        return add(pt.times(-1));
    }

    @Override
    public PointForAxes times(double times) {
        double[] ds = new double[size()];
        for (int i = 0; i < size(); i++) {
            ds[i] = times * get(i);
        }
        return new PointForAxes(ds);
    }

    public int size() {
        return values.length;
    }

    /**
     * 获取两个点中维度更高的那个
     *
     * @param p 相比较的点
     * @return 维度更高的那个点
     */
    public PointForAxes getBigger(PointForAxes p) {
        return size() > p.size() ? this : p;
    }

    /**
     * 返回对应的维度的值
     * 如不存在则返回0
     *
     * @param index 对应的维度 从0开始
     * @return 对应的维度的值
     */
    public double get(int index) {
        return index < values.length ? values[index] : 0;
    }

    /**
     * 向某一维度添加值
     *
     * @param index 维度
     * @param value 添加值
     * @return 生成的点
     */
    public PointForAxes add(int index, double value) {
        double[] ds;
        if (index < size()) {
            ds = values.clone();
        } else {
            ds = new double[index];
            for (int i = 0; i < index; i++) {
                ds[i] = get(i);
            }
        }
        ds[index] = ds[index] + value;
        return new PointForAxes(ds);
    }
}
