package rainbow

import rainbow.controller.SystemController
import rainbow.coordinates.CartesianCoordinateSystem3D
import rainbow.coordinates.two.CartesianCoordinateSystem2D
import rainbow.coordinates.two.PolarCoordinateSystem
import rainbow.function.mathfunction.simple._2D.trigonometric_function.Cos
import rainbow.function.mathfunction.simple._2D.trigonometric_function.Sin
import rainbow.function.pointfunction.Hypercube

/**
 * start
 */
fun main(args: Array<String>) {

    SystemController {

        //        task = {
        //            (coordinateSystem as CoordinateSystem2D).rotate(- R.001)
        //        }

        setCoordinateSystem(CartesianCoordinateSystem3D()) {

            //            this.painter.paintParts[0].visible = false

//            camera = Point3D(-5, 0, 10)

            //            painter.visible = false
        }

        addFunction(Cos(5.0, 5.0, 0.0))
//        addFunction(Hypercube(2,4.5))

    }
}