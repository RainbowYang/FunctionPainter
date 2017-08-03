package rainbow

import rainbow.build.CoordinateSystemBuilderUser
import rainbow.coordinates.PolarCoordinateSystem
import rainbow.frame.MainFrame
import rainbow.function.CoordinateFunction
import rainbow.function.pointfunction.RegularPolygon
import rainbow.utils.fromJson
import rainbow.utils.toJsonWhenExpose


fun main(args: Array<String>) {
    var coordinateSystem = PolarCoordinateSystem()
//    val coordinateSystem = CartesianCoordinateSystem(3)
    val json = coordinateSystem.toJsonWhenExpose()
//            .fromJson<CoordinateSystemData>()

    coordinateSystem = CoordinateSystemBuilderUser.build(json) as PolarCoordinateSystem

    with(coordinateSystem) {
        //        coordinateSystem.paintComponent.visible = false
        x = 500.0
        y = 500.0
    }


    val functions = mutableListOf<CoordinateFunction>()

    functions.apply {
        add(RegularPolygon(6, 10.0, 2).toJsonWhenExpose().fromJson<RegularPolygon>())
//        add(Lissajous(3, 4, 5).toJson().fromJson())
    }.forEach {
        it.coordinateSystem = coordinateSystem
        it.init()
    }

    MainFrame(coordinateSystem, functions)
}