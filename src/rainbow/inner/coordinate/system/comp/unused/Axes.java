package rainbow.inner.coordinate.system.comp.unused;

import rainbow.inner.coordinate.system.CoordinateSystem;

import java.util.ArrayList;

/**
 * CoordinateSystem中的组件
 * 用于存放所有的轴(Axis),并提供相关功能
 *
 * @author Rainbow Yang
 * @see Axis
 */
public class Axes implements CoordinateSystemComponent {
    private CoordinateSystem cs;

    public Axes(CoordinateSystem cs) {
        this.cs = cs;
    }

    //所有的轴
    private ArrayList<Axis> axes = new ArrayList<>();

    //所有轴开始算起的角度，用于整体旋转
    private double startAngle = 0;

    //所有轴长度的倍数，用于整体缩放
    private double allLengthTimes = 1;


    //Axes-start

    /**
     * 获取第index个坐标系
     *
     * @param index 索引
     * @return 返回的坐标系
     */
    public Axis getAxis(int index) {
        return axes.get(index);
    }

    /**
     * 添加count个轴,angle为0
     *
     * @param count 要添加的轴的个数
     */
    public void addAxes(int count) {
        for (int i = 0; i < count; i++) {
            axes.add(new Axis());
        }
    }

    /**
     * 添加轴,angle分别为angles里的各个值，所以会添加angles.length个轴
     *
     * @param angles 所要添加的各个轴的角度值
     */
    public void addAxes(double... angles) {
        for (double angle : angles) {
            axes.add(new Axis(angle));
        }
    }

    /**
     * 删除count个轴
     *
     * @param count 要删除的轴的个数
     */
    public void reduceAxes(int count) {
        for (int i = 0; i < count; i++) {
            axes.remove(axes.size() - 1 - i);
        }
    }
    //Axes-end

    // /**
    //  * 返回第index个轴
    //  * <br>从0开始</br>
    //  *
    //  * @param index 所要返回的轴的位置
    //  * @return 返回轴
    //  */
    // public Axis getAxis(int index) {
    //     return axes.get(index);
    // }

    //angle-start

    /**
     * 返回第index个轴的角度
     * <br>从0开始</br>
     *
     * @param index 所要返回的轴的位置
     * @return 返回轴的角度
     */
    public double getAngle(int index) {
        return axes.get(index).getAngle() + startAngle;
    }

    /**
     * 返回第index个轴的原始角度
     * <br>从0开始</br>
     *
     * @param index 所要返回的轴的位置
     * @return 返回轴的原始角度
     */
    public double getOriginalAngle(int index) {
        return axes.get(index).getAngle();
    }

    /**
     * 设置第index个轴的角度
     *
     * @param index 轴的位置
     * @param angle 所要设置的角度
     */
    public void setAngle(int index, double angle) {
        axes.get(index).setAngle(angle);
    }

    /**
     * 增加第index个轴的角度
     *
     * @param index 轴的位置
     * @param angle 所要增加的角度
     */
    public void addAngle(int index, double angle) {
        axes.get(index).addAngle(angle);
    }

    /**
     * 增加所有轴的角度
     *
     * @param angle 所要增加的角度
     */
    public void addAngle(double angle) {
        startAngle += angle;
    }

    //angle-end


    //length-start

    /**
     * 返回第index个轴的长度
     * <br>从0开始</br>
     *
     * @param index 所要返回的轴的位置
     * @return 返回轴的长度
     */
    public double getLength(int index) {
        return getOriginalLength(index) * getLengthTimes(index) * allLengthTimes;
    }

    /**
     * 返回第index个轴的原始长度
     * <br>从0开始</br>
     *
     * @param index 所要返回的轴的位置
     * @return 返回轴的原始长度
     */
    public double getOriginalLength(int index) {
        return axes.get(index).getLength();
    }

    /**
     * 设置第index个轴的原始长度
     *
     * @param index  轴的位置
     * @param length 所要设置的原始长度
     */
    public void setLength(int index, double length) {
        axes.get(index).setLength(length);
    }

    /**
     * 设置所有轴的原始长度
     *
     * @param length 所要设置的原始长度
     */
    public void setLength(double length) {
        axes.forEach(axis -> axis.setLength(length));
    }

    /**
     * 增加第index个轴的原始长度
     *
     * @param index  轴的位置
     * @param length 所要增加的原始长度
     */
    public void addLength(int index, double length) {
        axes.get(index).addLength(length);
    }

    /**
     * 增加所有轴的原始长度
     *
     * @param length 所要增加的原始长度
     */
    public void addLength(double length) {
        axes.forEach(axis -> axis.addLength(length));
    }

    //length-end

    //lengthTimes-start

    /**
     * 返回第index个轴的长度倍数
     * <br>从0开始</br>
     *
     * @param index 所要返回的轴的位置
     * @return 返回轴的长度倍数
     */
    public double getLengthTimes(int index) {
        return axes.get(index).getLengthTimes();
    }

    /**
     * 设置第index个轴的长度倍数
     *
     * @param index       轴的位置
     * @param lengthTimes 所要设置的长度倍数
     */
    public void setLengthTimes(int index, double lengthTimes) {
        axes.get(index).setLengthTimes(lengthTimes);
    }

    /**
     * 加倍第index个轴的长度倍数
     *
     * @param times 所要加倍的长度倍数
     */
    public void timesLengthTimes(double times) {
        axes.forEach(axis -> axis.timesLengthTimes(times));
    }

    public double getAllLengthTimes() {
        return allLengthTimes;
    }

    public void setAllLengthTimes(double allLengthTimes) {
        this.allLengthTimes = allLengthTimes;
    }

    public void timesAllLengthTimes(double times) {
        this.allLengthTimes *= times;
    }

    //lengthTimes-end

    /**
     * 对调两个维度的位置
     *
     * @param index1 维度1
     * @param index2 维度2
     */
    public void change(int index1, int index2) {
        Axis axis = axes.get(index1);
        axes.set(index1, axes.get(index2));
        axes.set(index2, axis);
    }

    /**
     * 返回轴的个数
     *
     * @return 轴的个数
     */
    public int getSize() {
        return axes.size();
    }

    /**
     * 返回开始的角度
     *
     * @return 返回开始的角度
     */
    public double getStartAngle() {
        return startAngle;
    }

    /**
     * 设置开始角度
     *
     * @param startAngle 开始角度
     */
    public void setStartAngle(double startAngle) {
        this.startAngle = startAngle;
    }

    /**
     * 增加开始角度
     *
     * @param startAngle 增加开始角度量
     */
    public void addStartAngle(double startAngle) {
        this.startAngle += startAngle;
    }

    @Override
    public String getKeyName() {
        return staticGetKeyName();
    }

    public static String staticGetKeyName() {
        return "Axes";
    }

    public static class Axis {
        //所指角度,表示为与三点钟方向的夹角，逆时针增加
        private double angle;
        //单位长度及其实际倍数
        private double length, lengthTimes = 1;

        public static double DEFAULT_LENGTH = 40;

        /**
         * 长度默认为40
         */
        public Axis() {
            this.angle = 0;
            this.length = 40;
        }

        /**
         * 长度默认为40
         *
         * @param angle 所指向的角度
         */
        public Axis(double angle) {
            this.angle = angle;
            this.length = DEFAULT_LENGTH;
        }

        public Axis(double angle, double length) {
            this.angle = angle;
            this.length = length;
        }

        public double getAngle() {
            return angle;
        }

        public void setAngle(double angle) {
            this.angle = angle;
        }

        public void addAngle(double angle) {
            this.angle += angle;
        }

        public double getLength() {
            return length;
        }

        public void setLength(double length) {
            this.length = length;
        }

        public void addLength(double length) {
            this.length += length;
        }

        public void timesLength(double times) {
            this.length *= times;
        }

        public double getLengthTimes() {
            return lengthTimes;
        }

        public void setLengthTimes(double lengthTimes) {
            this.lengthTimes = lengthTimes;
        }

        public void timesLengthTimes(double times) {
            this.lengthTimes *= times;
        }
    }
}
