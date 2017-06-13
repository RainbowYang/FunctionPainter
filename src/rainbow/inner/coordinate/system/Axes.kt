package rainbow.inner.coordinate.system

/**
 * @author Rainbow Yang
 */
class Axes {
    val axes = mutableListOf<rainbow.inner.coordinate.system.Axes.Axis>()

    //所有轴开始算起的角度，用于整体旋转
    var startAngle = 0.0
    //所有轴长度的倍数，用于整体缩放
    var allLengthTimes = 1.0

    fun addAxis(angle: Double) = axes.add(Axis(angle))
    fun addAxis(angle: Int) = addAxis(angle.toDouble())
    fun addAxisDeg(angle: Double) = addAxis(Math.toRadians(angle))
    fun addAxisDeg(angle: Int) = addAxisDeg(angle.toDouble())

    fun getAngle(index: Int) = axes[index].angle + startAngle
    fun getLength(index: Int) = axes[index].length * axes[index].lengthTimes * allLengthTimes
    fun setAngle(index: Int, angle: Double) {
        axes[index].angle = angle
    }

    fun setLengthTimes(index: Int, lengthTimes: Double) {
        axes[index].lengthTimes = lengthTimes
    }

    fun getSize() = axes.size

    /**
     * 对调两个维度的位置
     * @param index1 维度1
     * *
     * @param index2 维度2
     */
    fun change(index1: Int, index2: Int) {
        val axis = axes[index1]
        axes[index1] = axes[index2]
        axes[index2] = axis
    }

    class Axis(var angle: Double = 0.0, var length: Double = 40.0, var lengthTimes: Double = 1.0)
}