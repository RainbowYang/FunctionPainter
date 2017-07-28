package rainbow.function

import rainbow.point.CoordinatePoint
import rainbow.utils.CoordinateGraphics

/**
 * 重写paintMain(),对点进行连线
 * @author Rainbow Yang
 */
abstract class PointsFunction : CoordinateFunction() {

    override var paintComponent: CoordinateFunctionPainter
            = PointsFunctionPainter(this)

    var pointsList = mutableListOf<MutableList<CoordinatePoint>>()

    override fun init() = calcPoints()

    open fun calcPoints() {}

    fun cleanAllPoints() = pointsList.clear()
    fun newPointList() = pointsList.add(mutableListOf())
    fun addPoint(coordinatePoint: CoordinatePoint) {
        if (pointsList.isEmpty()) newPointList()

        pointsList.last().add(coordinatePoint)
    }

    class PointsFunctionPainter(val function: PointsFunction) : CoordinateFunctionPainter() {
        override fun paintMain(cg: CoordinateGraphics) {
            function.pointsList.forEach { cg.paintCoordinatePoints(it) }
        }
    }

}
