package rainbow.inner.coordinate.system.location_changer

import rainbow.inner.coordinate.point.MyPoint
import rainbow.inner.coordinate.point.PointDouble
import rainbow.inner.coordinate.point.PointForAxes
import rainbow.inner.coordinate.system.CoordinateSystem

/**
 * @author Rainbow Yang
 */
class LocationChangerOfCoordinateSystemForAxes(val cs: CoordinateSystem) : LocationChanger {
    override fun toReal(p: MyPoint): PointDouble {
        val pa = p.toPointForAxes()
        var px = cs.x
        var py = cs.y//x,y是原点的在屏幕上的坐标
        val axes = cs.axes
        for (i in 0..axes.size - 1) {
            val angle = axes.getAngle(i)
            val length = axes.getLength(i)
            px += Math.cos(angle) * length * pa.getValue(i)
            py -= Math.sin(angle) * length * pa.getValue(i)
        }
        return PointDouble(px, py)
    }

    override fun toSystem(p: PointDouble): MyPoint {
        val axes = cs.axes
        val dp = PointDouble(p.x - cs.x, cs.y - p.y)
        val x = dp.spin(Math.PI / 2 - axes.getAngle(1)).x
        val y = dp.spin(0 - axes.getAngle(0)).y

        val xAngle = axes.getAngle(0) + Math.PI / 2 - axes.getAngle(1)
        val yAngle = axes.getAngle(1) - axes.getAngle(0)

        val px = x / Math.cos(xAngle) / axes.getLength(0)
        val py = y / Math.sin(yAngle) / axes.getLength(1)

        return PointForAxes(px, py)
    }
}