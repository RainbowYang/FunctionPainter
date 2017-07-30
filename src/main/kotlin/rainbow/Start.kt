package rainbow

import rainbow.coordinates.PolarCoordinateSystem
import rainbow.frame.MainFrame
import rainbow.function.CoordinateFunction
import rainbow.function.pointfunction.RegularPolygon

fun main(args: Array<String>) {

    val coordinateSystem = PolarCoordinateSystem()
//    val coordinateSystem = CartesianCoordinateSystem(2)
    with(coordinateSystem) {
        x = 500.0
        y = 500.0
    }

    val functions = mutableListOf<CoordinateFunction>()

    functions.apply {
        add(RegularPolygon(6, 10.0, 2))
    }.forEach {
        it.coordinateSystem = coordinateSystem
        it.init()
    }

    MainFrame(coordinateSystem, functions)
}