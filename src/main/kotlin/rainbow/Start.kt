package rainbow

import rainbow.coordinates.CartesianCoordinateSystemBall
import rainbow.frame.MainFrame
import rainbow.function.CoordinateFunction
import rainbow.function.mathfunction.special.Lissajous
import rainbow.function.pointfunction.RegularPolygon
import rainbow.utils.fromJson
import rainbow.utils.toJsonWhenExpose


fun main(args: Array<String>) {
    var coordinateSystem = CartesianCoordinateSystemBall(3)

    with(coordinateSystem) {
        x = 500.0
        y = 500.0
    }


    val functions = mutableListOf<CoordinateFunction>()

    functions.apply {
        add(RegularPolygon(6, 10.0, 2).toJsonWhenExpose().fromJson<RegularPolygon>())
        add(Lissajous(3, 4, 5))
    }.forEach {
        it.coordinateSystem = coordinateSystem
        it.init()
    }

    MainFrame(coordinateSystem, functions)
}