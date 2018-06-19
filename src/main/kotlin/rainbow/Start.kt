package rainbow

import rainbow.controller.SystemController
import rainbow.coordinates.two.PolarCoordinateSystem
import rainbow.function.mathfunction.simple._2D.trigonometric_function.Sin

/**
 * start
 */
fun main(args: Array<String>) {

    SystemController {

        //        task = {
        //            (coordinateSystem as CoordinateSystem2D).rotate(- R.001)
        //        }

        setCoordinateSystem(PolarCoordinateSystem()) {

//            this.painter.paintParts[0].visible = false

//            camera = Point3D(-5, 0, 10)

            //            painter.visible = false
        }

        addFunction(Sin(5.0, 2.0, 4.0))

    }
}