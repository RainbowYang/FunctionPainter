package rainbow

import rainbow.coordinates.CartesianCoordinateSystemBall
import rainbow.coordinates.PolarCoordinateSystem
import rainbow.function.mathfunction.special.Lissajous


fun main(args: Array<String>) {

    MainSystem {
        fps = 60

//        task = {
//            (coordinateSystem as CoordinateSystem2D).rotate(0.001)
//        }

        setCoordinateSystem(CartesianCoordinateSystemBall(3)) {
            x = 500.0
            y = 500.0

            painter.visible = false
        }

//        addFunction(RegularPolygon(100, 10.0, 60))
        addFunction(Lissajous(3, 4, 5))
    }
}