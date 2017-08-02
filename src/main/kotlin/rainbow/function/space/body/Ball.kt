package rainbow.function.space.body

import moe.rainbow.body.Body
import moe.rainbow.point.Point3D
import moe.rainbow.utils.DEFAULT_LOCATION
import moe.rainbow.utils.DEFAULT_VELOCITY

/**
 * 一个球，拥有[radius]
 *
 * @author Rainbow Yang
 */
class Ball(var radius: Double = 0.0,
           location: Point3D = DEFAULT_LOCATION,
           velocity: Point3D = DEFAULT_VELOCITY,
           mass: Number = 0
) : Body(location, velocity, mass.toDouble()) {

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