package rainbow.point

import rainbow.utils.asPoint2DPolar
import java.util.*

/**
 * 任意维的球坐标点
 * 由一个radius和多个angle组成
 *
 * @author Rainbow Yang
 */
class PointSpherical(val radius: Double, vararg angles: Double) : CoordinatePoint {

    val angles = angles

    operator fun component1() = radius
    operator fun component2() = angles

    companion object {
        operator fun invoke(cp: CoordinatePoint): PointSpherical {
            val values = cp.asAxes.values

            var radius = values[0]
            val angles = mutableListOf<Double>()

            (1 until values.size).forEach {
                val height = values[it]
                val (r, angle) = Point2D(radius, height).asPoint2DPolar

                radius = r
                angles.add(angle)
            }

            return PointSpherical(radius, *angles.toDoubleArray())

        }
    }

    override val asAxes by lazy {
        val values = mutableListOf<Double>()

        var rest = radius

        angles.reversed().forEach {
            values.add(rest * Math.sin(it))
            rest *= Math.cos(it)
        }

        values.add(rest)

        values.reverse()

        PointAxes(values)
    }

    override fun toString(): String {
        return "PointSpherical(radius=$radius, angles=${Arrays.toString(angles)})"
    }


}