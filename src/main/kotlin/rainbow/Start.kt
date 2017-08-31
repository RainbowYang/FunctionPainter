package rainbow

import rainbow.coordinates.PolarCoordinateSystem
import rainbow.function.pointfunction.RegularPolygon


fun main(args: Array<String>) {

    MainSystem {

        fps = 60

        task = {
            (coordinateSystem as PolarCoordinateSystem).rotate(0.001)
        }

        setCoordinateSystem(PolarCoordinateSystem()) {
            x = 500.0
            y = 500.0

            painter.visible = false
        }

        addFunction(RegularPolygon(100, 10.0, 60))

    }.run()
}