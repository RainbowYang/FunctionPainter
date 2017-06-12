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

    fun getAngle(index: Int) = axes.get(index).angle + startAngle
    fun getLength(index: Int) = axes.get(index).length * axes.get(index).lengthTimes * allLengthTimes

    fun getSize() = axes.size

    class Axis(var angle: Double = 0.0, var length: Double = 40.0, var lengthTimes: Double = 1.0)
}