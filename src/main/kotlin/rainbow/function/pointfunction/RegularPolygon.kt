package rainbow.function.pointfunction

import rainbow.function.PointsFunction
import rainbow.point.Point2DPolar

/**
 * 正多边形
 * @author Rainbow Yang
 */
class RegularPolygon(var sides: Int = 6,
                     var radius: Double = 5.0,
                     var step: Int = 1,
                     var startAngle: Double = Math.PI / 2) : PointsFunction() {

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

    private fun addPointByIndex(index: Int) = addPoint(getPoint(index))

    private fun getPoint(index: Int): Point2DPolar {
        val stepAngle = Math.PI * 2 / sides
        return Point2DPolar(radius, startAngle + index * stepAngle)
    }

    override fun toString(): String {
        return "RegularPolygon(sides=$sides, radius=$radius, step=$step, startAngle=$startAngle)"
    }
}
