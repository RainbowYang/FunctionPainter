package rainbow.coordinate.function

import rainbow.coordinate.graphics.CoordinateGraphics
import rainbow.coordinate.point.CoordinatePoint
import rainbow.coordinate.system.CoordinateSystem

/**
 * 重写paintMain(),对点进行连线
 * @author Rainbow Yang
 */
abstract class PointsFunction : CoordinateFunction() {
    var pointsList = mutableListOf<MutableList<CoordinatePoint>>()

    override var coordinateSystem: CoordinateSystem
        get() = super.coordinateSystem
        set(value) {
            painter = PointsFunctionPainter(this, value)
        }

    override fun init() = calcPoints()

    open fun calcPoints() {}

    fun cleanAllPoints() = pointsList.clear()
    fun newPointList() = pointsList.add(mutableListOf())
    fun addPoint(coordinatePoint: CoordinatePoint) {
        if (pointsList.isEmpty())
            newPointList()

        pointsList.last().add(coordinatePoint)
    }

}

class PointsFunctionPainter(function: PointsFunction, coordinateSystem: CoordinateSystem) :
        CoordinateFunctionPainter<PointsFunction>(function, coordinateSystem) {
    override fun paintMain(cg: CoordinateGraphics) {
        function.pointsList.forEach { cg.paintCoordinatePoints(it) }
    }
}
