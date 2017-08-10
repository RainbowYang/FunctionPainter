package rainbow.point

import rainbow.utils.component1
import rainbow.utils.component2
import rainbow.utils.component3

/**
 * 三维点
 * @author Rainbow Yang
 */
class Point3D(val x: Double = 0.0, val y: Double = 0.0, val z: Double = 0.0) : CoordinatePoint {
    constructor(x: Number = 0, y: Number = 0, z: Number = 0) : this(x.toDouble(), y.toDouble(), z.toDouble())

    companion object {
        operator fun invoke(form: CoordinatePoint): Point3D {
            val (x, y, z) = form.asAxes
            return Point3D(x, y, z)
        }

        val ZERO = Point3D(0.0, 0.0, 0.0)
    }

    override val asAxes by lazy { PointAxes(x, y, z) }

    override fun toString() = "Point3D(x=$x, y=$y, z=$z)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Point3D

        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + z.hashCode()
        return result
    }


}