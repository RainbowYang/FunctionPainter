package rainbow.coordinate.function.pointfunction

import rainbow.coordinate.function.PointsFunction
import rainbow.coordinate.point.PointPolar2D

/**
 * 正多边形
 * @author Rainbow Yang
 */
class RegularPolygon(
        var sides: Int = 6,
        var radius: Double = 5.0,
        var step: Int = 1,
        var startAngle: Double = Math.PI / 2
) : PointsFunction() {


    override fun calcPoints() {
        cleanAllPoints()

        val hasPoint = BooleanArray(sides)

        for (location in 0..sides - 1) {
            if (hasPoint[location]) continue

            newPointList()

            var index = location

            while (true) {
                addPointByIndex(index)
                hasPoint[index] = true

                index += step
                index %= sides

                if (index == location) break
            }

            addPointByIndex(location)
        }
    }

    fun addPointByIndex(index: Int) = addPoint(getPoint(index))

    private fun getPoint(index: Int): PointPolar2D {
        val stepAngle = Math.PI * 2 / sides
        return PointPolar2D(radius, startAngle + index * stepAngle)
    }

    override fun toString(): String {
        return "RegularPolygon(sides=$sides, radius=$radius, step=$step, startAngle=$startAngle)"
    }
}
