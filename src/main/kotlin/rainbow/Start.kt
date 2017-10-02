package rainbow

import rainbow.coordinates.two.CartesianCoordinateSystem2D
import rainbow.function.mathfunction.special.Lissajous


fun main(args: Array<String>) {

    MainSystem {
        fps = 60

//        task = {
//            (coordinateSystem as CoordinateSystem2D).rotate(0.001)
//        }

        setCoordinateSystem(CartesianCoordinateSystem2D()) {
            x = 500.0
            y = 500.0

//            painter.visible = false
        }

//        addFunction(RegularPolygon(100, 10.0, 60))
        addFunction(Lissajous(3, 4))
    }
}