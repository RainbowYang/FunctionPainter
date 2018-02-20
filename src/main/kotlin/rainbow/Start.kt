package rainbow

import rainbow.controller.SystemController
import rainbow.coordinates.CartesianCoordinateSystem3D
import rainbow.coordinates.CartesianCoordinateSystem4D
import rainbow.coordinates.two.CartesianCoordinateSystem2D
import rainbow.function.Parallelotope
import rainbow.function.mathfunction.simple._2D.trigonometric_function.Sin
import rainbow.function.mathfunction.special.Lissajous
import rainbow.function.space.body.Ball
import rainbow.function.space.world.World
import rainbow.point.Point3D
import rainbow.point.PointAxes

/**
 * start
 */
fun main(args: Array<String>) {

    SystemController {

        //        task = {
        //            (coordinateSystem as CoordinateSystem2D).rotate(- R.001)
        //        }

        setCoordinateSystem(CartesianCoordinateSystem3D()) {

//            camera = Point3D(-5, 0, 10)

            //            painter.visible = false
        }

        addFunction(Sin(5.0,2.0,4.0))

    }
}