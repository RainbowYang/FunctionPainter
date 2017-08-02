package rainbow.point

import rainbow.utils.component1
import rainbow.utils.component2
import rainbow.utils.component3

/**
 * 三维点 亦可作为向量
 * @author Rainbow Yang
 */
class Point3D(val x: Double = 0.0, val y: Double = 0.0, val z: Double = 0.0) : CoordinatePoint {
    constructor(x: Number = 0, y: Number = 0, z: Number = 0) :
            this(x.toDouble(), y.toDouble(), z.toDouble())

    companion object {
        operator fun invoke(form: CoordinatePoint): Point3D {
            val (x, y, z) = form.asAxes
            return Point3D(x, y, z)
        }

        val ZERO = Point3D(0.0, 0.0, 0.0)
    }

    override val asAxes get() = PointAxes(x, y, z)

    override fun toString(): String {
        return "Point3D(x=$x, y=$y, z=$z)"
    }

}