package rainbow

import rainbow.coordinates.CartesianCoordinateSystem
import rainbow.frame.MainFrame
import rainbow.function.CoordinateFunction
import rainbow.function.mathfunction.special.Lissajous


fun main(args: Array<String>) {
//    val coordinateSystem = PolarCoordinateSystem()
    val coordinateSystem = CartesianCoordinateSystem(3)
    with(coordinateSystem) {
        x = 500.0
        y = 500.0
    }

    val functions = mutableListOf<CoordinateFunction>()

    functions.apply {
        //        add(RegularPolygon(6, 10.0, 2))
        add(Lissajous(3, 4, 5))
    }.forEach {
        it.coordinateSystem = coordinateSystem
        it.init()
    }

    MainFrame(coordinateSystem, functions)
}