package rainbow

import rainbow.coordinates.CartesianCoordinateSystemBall
import rainbow.frame.MainFrame
import rainbow.function.CoordinateFunction
import rainbow.function.pointfunction.RegularPolygon
import rainbow.point.Point2D


fun main(args: Array<String>) {
    val coordinateSystem = CartesianCoordinateSystemBall()

    with(coordinateSystem) {
        x = 500.0
        y = 500.0
        sightAsDegree = 30 to 30
        resetAngleAndLength()
    }

    val point = coordinateSystem.toScreenPoint(Point2D(1, 1))


    val functions = mutableListOf<CoordinateFunction>()

    functions.apply {
        add(RegularPolygon(100, 10.0, 60))
    }.forEach {
        it.coordinateSystem = coordinateSystem
        it.init()
    }

    MainFrame(coordinateSystem, functions)
}