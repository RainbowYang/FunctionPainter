package rainbow

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import rainbow.coordinates.PolarCoordinateSystem
import rainbow.frame.MainFrame
import rainbow.function.CoordinateFunction
import rainbow.function.mathfunction.special.Lissajous
import sun.plugin2.util.PojoUtil.toJson


fun main(args: Array<String>) {
    val coordinateSystem = PolarCoordinateSystem()
//    val coordinateSystem = CartesianCoordinateSystem(3)
    with(coordinateSystem) {
        //        coordinateSystem.paintComponent.visible = false
        x = 500.0
        y = 500.0
    }

    println(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(coordinateSystem))

    val functions = mutableListOf<CoordinateFunction>()

    functions.apply {
        //        add(RegularPolygon(6, 10.0, 2))
//        add(Lissajous(3, 4, 5))
    }.forEach {
        it.coordinateSystem = coordinateSystem
        it.init()
    }

    MainFrame(coordinateSystem, functions)
}