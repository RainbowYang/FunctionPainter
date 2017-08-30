package rainbow.function

import rainbow.point.CoordinatePoint
import rainbow.utils.CoordinateGraphics

/**
 * 重写paintMain(),对点进行连线
 * @author Rainbow Yang
 */
abstract class PointsFunction : CoordinateFunction() {

    override var painter: Painter = PointsFunctionPainter()

    var pointsList = mutableListOf<MutableList<CoordinatePoint>>()

    override fun init() = calcPoints()

    open fun calcPoints() {}

    fun cleanAllPoints() = pointsList.clear()
    fun newPointList() = pointsList.add(mutableListOf())
    fun addPoint(coordinatePoint: CoordinatePoint) {
        if (pointsList.isEmpty()) newPointList()

        pointsList.last().add(coordinatePoint)
    }

    inner class PointsFunctionPainter : CoordinateFunction.Painter() {
        override fun paintMain(cg: CoordinateGraphics) {
            pointsList.forEach { cg.paintCoordinatePoints(it) }
        }
    }

}
