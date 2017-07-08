package rainbow.coordinate.system.cartesian

/**
 * For CartesianCoordinateSystem
 *
 * 用于存储Axis及其子类
 *
 * @author Rainbow Yang
 */
class Axes {
    val axes = mutableListOf<Axis>()

    fun addAxis(axis: Axis) = axes.add(axis)

    fun addAxis(angle: Double) = axes.add(Axis(angle))
    fun addAxis(angle: Int) = addAxis(angle.toDouble())
    fun addAxisDeg(angle: Double) = addAxis(Math.toRadians(angle))
    fun addAxisDeg(angle: Int) = addAxisDeg(angle.toDouble())

    fun getAngle(index: Int) = axes[index].angle
    fun getLength(index: Int) = axes[index].length

    val size
        get() = axes.size

    /**
     * 对调两个维度的位置
     * @param index1 维度1
     * @param index2 维度2
     */
    fun change(index1: Int, index2: Int) {
        val axis = axes[index1]
        axes[index1] = axes[index2]
        axes[index2] = axis
    }
}