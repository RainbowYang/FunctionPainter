package rainbow.function

import rainbow.point.CoordinatePoint
import rainbow.point.PointAxes
import rainbow.utils.CoordinateGraphics
import java.util.*

/**
 * 任意维度的平行体
 * @author Rainbow Yang
 */
class Parallelotope(val origin: CoordinatePoint, vararg towardsList: CoordinatePoint) : CoordinateFunction() {

    val relativelyTowards = towardsList.map { it - origin }

    val size = towardsList.size

    val lines = mutableListOf<Pair<Point, Point>>()

    override var painter: Painter = ParallelotopePainter()

    inner class ParallelotopePainter : CoordinateFunction.Painter() {
        override fun paintMain(cg: CoordinateGraphics) {
            lines.forEach { (first, second) ->
                cg.paintLine(first.location + origin, second.location + origin)
            }
        }
    }

    init {
        createLine()
        println()
    }

    fun createLine(origin: Point = Point()) {
        origin.getNextLocations(relativelyTowards).forEach {
            lines.add(origin to it)
            createLine(it)
        }
    }

    class Point(vararg locations: CoordinatePoint) {
        val locations = locations
        val location: CoordinatePoint
            get() {
                var sum: CoordinatePoint = PointAxes.ZERO
                locations.forEach { sum += it }
                return sum
            }

        fun getNextLocations(relativelyTowards: List<CoordinatePoint>) =
                relativelyTowards.filter { !locations.contains(it) }.map { Point(*locations, it) }

        override fun toString(): String {
            return "Point(location=$location)"
        }


    }

}