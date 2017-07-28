package rainbow.start

import rainbow.coordinate.System
import rainbow.function.mathfunction.simple._2D.LogFunction
import rainbow.coordinates.CartesianCoordinateSystem
import rainbow.frame.MainFrame

fun main(args: Array<String>) {

    val coordinateSystem = CartesianCoordinateSystem(4, 200.0, 200.0)
    val system = System(coordinateSystem)

    system.coordinateFunctions.functions.add(
            LogFunction(1.0, 2.0).apply {
                this.coordinateSystem = coordinateSystem
                init()
            }
    )

    MainFrame(system)

}