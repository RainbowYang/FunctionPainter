package rainbow.inner.coordinate.system.cartesian

import rainbow.inner.coordinate.point.CoordinatePoint
import rainbow.inner.coordinate.point.PointDouble
import rainbow.inner.coordinate.point.PointForAxes
import rainbow.inner.coordinate.system.CoordinateSystem

/**
 * 任意维度的轴坐标系
 * @author Rainbow Yang
 */
class CartesianCoordinateSystem(size: Int) : CoordinateSystem() {
    val axes = Axes()

    init {
        when (size) {
            4 -> {
                axes.addAxisDeg(225)
                axes.addAxisDeg(0)
                axes.addAxisDeg(90)
                axes.addAxisDeg(135)
            }

            3 -> {
                axes.addAxisDeg(225)
                axes.addAxisDeg(0)
                axes.addAxisDeg(90)
            }

            2 -> {
                axes.addAxisDeg(0)
                axes.addAxisDeg(90)
            }

            1 -> axes.addAxisDeg(0)
        }
    }

    override fun _toScreen(cp: CoordinatePoint): PointDouble {
        val form = cp.toPointForAxes()
        var px = 0.0
        var py = 0.0
        for (i in 0..axes.size - 1) {
            val angle = axes.getAngle(i)
            val length = axes.getLength(i)
            px += Math.cos(angle) * length * form.getValue(i)
            py += Math.sin(angle) * length * form.getValue(i)
        }
        return PointDouble(px, py)
    }

    override fun _toSystem(pd: PointDouble): CoordinatePoint {
        val x = pd.spin(Math.PI / 2 - axes.getAngle(1)).x
        val y = pd.spin(0 - axes.getAngle(0)).y

        val xAngle = axes.getAngle(0) + Math.PI / 2 - axes.getAngle(1)
        val yAngle = axes.getAngle(1) - axes.getAngle(0)

        val px = x / Math.cos(xAngle) / axes.getLength(0)
        val py = y / Math.sin(yAngle) / axes.getLength(1)

        return PointForAxes(px, py)
    }
}