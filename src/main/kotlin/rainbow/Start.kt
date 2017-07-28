package rainbow

import rainbow.coordinates.CartesianCoordinateSystem
import rainbow.frame.MainFrame
import rainbow.function.CoordinateFunction
import rainbow.function.pointfunction.Hypercube

fun main(args: Array<String>) {

    val coordinateSystem = CartesianCoordinateSystem(2)
    with(coordinateSystem) {
        x = 500.0
        y = 500.0
    }

    val functions = mutableListOf<CoordinateFunction>()

    functions.apply {
        add(Hypercube(2, 5.5))
    }.forEach {
        it.coordinateSystem = coordinateSystem
        it.init()
    }

    MainFrame(coordinateSystem, functions)
}