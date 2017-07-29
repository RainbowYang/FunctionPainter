package rainbow

import rainbow.coordinates.CartesianCoordinateSystem
import rainbow.frame.MainFrame
import rainbow.function.CoordinateFunction
import rainbow.function.pointfunction.RegularPolygon

fun main(args: Array<String>) {

    val coordinateSystem = CartesianCoordinateSystem(2)
    with(coordinateSystem) {
        x = 500.0
        y = 500.0
        paintComponent.isVisual = false
    }

    val functions = mutableListOf<CoordinateFunction>()

    functions.apply {
        add(RegularPolygon(1600, 100.0, 123))
    }.forEach {
        it.coordinateSystem = coordinateSystem
        it.init()
    }

    MainFrame(coordinateSystem, functions)
}