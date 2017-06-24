package rainbow.inner.coordinate.system

import rainbow.inner.coordinate.point.MyPoint
import rainbow.inner.coordinate.point.PointDouble
import rainbow.inner.coordinate.point.PointForAxes
import java.lang.Math.*

/**
 * 任意维度的轴坐标系
 * @author Rainbow Yang
 */
class CoordinateSystemForAxes(size: Int) : CoordinateSystem() {
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

    override fun toReal(p: MyPoint): PointDouble {
        val pa = p.toPointForAxes()
        var px = x
        var py = y//x,y是原点的在屏幕上的坐标
        for (i in 0..axes.axes.size - 1) {
            val angle = axes.getAngle(i)
            val length = axes.getLength(i)
            px += cos(angle) * length * pa.getValue(i)
            py -= sin(angle) * length * pa.getValue(i)
        }
        return PointDouble(px, py)
    }

    override fun toSystem(p: PointDouble): MyPoint {
        val dp = rainbow.inner.coordinate.point.PointDouble(p.x - x, y - p.y)
        val x = dp.spin(PI / 2 - axes.getAngle(1)).x
        val y = dp.spin(0 - axes.getAngle(0)).y

        val xAngle = axes.getAngle(0) + PI / 2 - axes.getAngle(1)
        val yAngle = axes.getAngle(1) - axes.getAngle(0)

        val px = x / cos(xAngle) / axes.getLength(0)
        val py = y / sin(yAngle) / axes.getLength(1)

        return PointForAxes(px, py)
    }
}