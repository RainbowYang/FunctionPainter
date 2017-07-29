package rainbow.coordinates

import rainbow.component.CoordinateTransformComponent
import rainbow.point.CoordinatePoint
import rainbow.point.PointDouble
import rainbow.point.PointForAxes
import rainbow.utils.CoordinateGraphics
import java.lang.Math.*

/**
 * 任意维度的轴坐标系
 *
 * 但由于其任意纬度的特性，可能不会有专门的立体效果
 * 故继承[CoordinateSystem2D]
 *
 * @author Rainbow Yang
 * @see CoordinateSystem2D
 */
class CartesianCoordinateSystem(size: Int) : CoordinateSystem2D() {

    override var coordinateTransformComponent: CoordinateTransformComponent
            = CoordinateTransformComponentForCartesianCoordinateSystem(this)

    override var paintComponent: CoordinateSystemPainter
            = PainterForCartesianCoordinateSystem(this)

    val axes = mutableListOf<Axis>()

    val size: Int get() = axes.size

    init {
        when (size) {
            2 -> addAxes(0, 90)
            3 -> addAxes(225, 0, 90)
            else -> addAxes(List(size) { 0 })
        }

    }

    fun addAxes(angles: List<Number>) = addAxes(*angles.toTypedArray())
    fun addAxes(vararg angles: Number) = angles.forEach { axes.add(Axis(toRadians(it.toDouble()))) }

    /**
     * [angle]使用弧度
     */
    data class Axis(var angle: Double = 0.0, var length: Double = 40.0)

    open class CoordinateTransformComponentForCartesianCoordinateSystem(val system: CartesianCoordinateSystem) :
            CoordinateTransformComponent() {
        override fun toScreenPoint(cp: CoordinatePoint): PointDouble = with(system) {
            val form = cp.toPointForAxes()
            var px = 0.0
            var py = 0.0
            for (i in 0..axes.size - 1) {
                val (angle, length) = axes[i]
                px += cos(angle) * length * form.getValue(i)
                py += sin(angle) * length * form.getValue(i)
            }
            return rotateAndScaleAndMove(PointDouble(px, py))
        }


        override fun toCoordinatePoint(p: PointDouble): CoordinatePoint = with(system) {
            val pd = inverseRotateAndScaleAndMove(p)

            val x = pd.spin(Math.PI / 2 - axes[1].angle).x
            val y = pd.spin(0 - axes[0].angle).y

            val xAngle = axes[0].angle + Math.PI / 2 - axes[1].angle
            val yAngle = axes[1].angle - axes[0].angle

            val px = x / Math.cos(xAngle) / axes[0].length
            val py = y / Math.sin(yAngle) / axes[1].length

            return PointForAxes(px, py)

        }
    }

    open class PainterForCartesianCoordinateSystem(val system: CartesianCoordinateSystem) :
            CoordinateSystemPainter(system) {

        var paintRange = 0..30
        val sizeRange
            get() = 0..system.size

        override fun paintOrigin(cg: CoordinateGraphics) = cg.paintString("O", PointForAxes.ZERO)


        override fun paintGrid(cg: CoordinateGraphics) = with(system) {
            //维度遍历
            for (i in sizeRange) {
                //值遍历
                for (value in paintRange) {
                    val p0 = PointForAxes.ZERO.plusAtAndNew(i, value)
                    val p1 = p0.plusAtAndNew(if (i == axes.size - 1) 0 else i + 1, 1.0)
                    val p2 = p0.plusAtAndNew(if (i == 0) axes.size - 1 else i - 1, 1.0)

                    cg.paintRayLine(p0, p1)
                    cg.paintRayLine(p0, p2)
                }
            }
        }

        override fun paintAxes(cg: CoordinateGraphics) = with(system) {
            sizeRange.forEach {
                cg.paintStraightLine(PointForAxes.ZERO, PointForAxes.ZERO.plusAtAndNew(it, 10))
            }
        }

        override fun paintNumber(cg: CoordinateGraphics) = with(system) {
            sizeRange.forEach { size ->
                paintRange.filter { it != 0 }.forEach {
                    cg.paintString(it, PointForAxes.ZERO.plusAtAndNew(size, it))
                }
            }
        }
    }

}