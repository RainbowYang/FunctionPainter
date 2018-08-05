package rainbow.function.space.body

import rainbow.point.CoordinatePoint
import rainbow.point.Point3D
import rainbow.utils.CoordinateGraphics

/**
 * 一个球，拥有[radius]
 *
 * @author Rainbow Yang
 */
class Ball(var radius: Double = 0.0,
           location: Point3D = Point3D.ZERO,
           velocity: Point3D = Point3D.ZERO,
           mass: Number = 0
) : Body(location, velocity, mass.toDouble()) {

    override fun selfPaint(cg: CoordinateGraphics) {
        cg.paintCircle(location, location.asAxes.plusAtAndNew(0, radius))


    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        if (!super.equals(other)) return false

        other as Ball

        if (radius != other.radius) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + radius.hashCode()
        return result
    }
}