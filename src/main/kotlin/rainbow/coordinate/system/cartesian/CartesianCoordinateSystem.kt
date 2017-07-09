package rainbow.coordinate.system.cartesian

import rainbow.coordinate.point.CoordinatePoint
import rainbow.coordinate.point.PointDouble
import rainbow.coordinate.point.PointForAxes
import rainbow.coordinate.system.CoordinateSystem
import rainbow.coordinate.system.CoordinateSystem2D
import rainbow.paint.Painter

/**
 * 任意维度的轴坐标系
 * @author Rainbow Yang
 */
class CartesianCoordinateSystem(size: Int,
                                override var x: Double = 700.0,
                                override var y: Double = 500.0,
                                override var rotatedAngle: Double = 0.0,
                                override var zoomRate: Double = 1.0)
    : CoordinateSystem, CoordinateSystem2D {
    override var painter: Painter = PainterForCartesianCoordinateSystem(this)

    val axes = Axes(size)

    init {
        if (size <= 1)
            throw IllegalArgumentException("$size is not allowed in CartesianCoordinateSystem")
        when (size) {
            4 -> axes.setAngleByDegrees(225, 0, 90, 135)
            3 -> axes.setAngleByDegrees(225, 0, 90)
            2 -> axes.setAngleByDegrees(0, 90)
        }
    }

    override fun toScreenPoint(cp: CoordinatePoint): PointDouble {
        val form = cp.toPointForAxes()
        var px = 0.0
        var py = 0.0
        for (i in 0..axes.size - 1) {
            val (angle, length) = axes[i]
            px += Math.cos(angle) * length * form.getValue(i)
            py += Math.sin(angle) * length * form.getValue(i)
        }
        return rotateAndScaleAndMove(PointDouble(px, py))
    }

    //会返回在第一个面的值
    override fun toCoordinatePoint(p: PointDouble): CoordinatePoint {
        val pd = inverseRotateAndScaleAndMove(p)

        val x = pd.spin(Math.PI / 2 - axes[1].angle).x
        val y = pd.spin(0 - axes[0].angle).y

        val xAngle = axes[0].angle + Math.PI / 2 - axes[1].angle
        val yAngle = axes[1].angle - axes[0].angle

        val px = x / Math.cos(xAngle) / axes[0].length
        val py = y / Math.sin(yAngle) / axes[1].length

        return PointForAxes(px, py)
    }

    override fun toString(): String {
        return "CartesianCoordinateSystem(axes=$axes, x=$x, y=$y, rotatedAngle=$rotatedAngle, zoomRate=$zoomRate)"
    }
}
