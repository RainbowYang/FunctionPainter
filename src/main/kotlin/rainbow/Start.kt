package rainbow

import rainbow.controller.SystemController
import rainbow.coordinates.CartesianCoordinateSystem4D
import rainbow.coordinates.two.CartesianCoordinateSystem2D
import rainbow.function.mathfunction.simple._2D.trigonometric_function.Cos

/**
 * start
 */
fun main(args: Array<String>) {

    SystemController {

        //        task = {
        //            (coordinateSystem as CoordinateSystem2D).rotate(- R.001)
        //        }

        setCoordinateSystem(CartesianCoordinateSystem4D()) {

            //            this.painter.paintParts[0].visible = false

//            camera = Point3D(-5, 0, 10)

            //            painter.visible = false
        }

        addFunction(Cos(5.0, 5.0, 0.0))
//        addFunction(Hypercube(2,4.5))

    }
}