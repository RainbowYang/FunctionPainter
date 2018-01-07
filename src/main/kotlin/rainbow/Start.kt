package rainbow

import rainbow.controller.SystemController
import rainbow.coordinates.CartesianCoordinateSystem3D
import rainbow.coordinates.CartesianCoordinateSystem4D
import rainbow.function.Parallelotope
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

            camera = Point3D(-5, 0, 10)

            //            painter.visible = false
        }

        val world = World()
        world.addBody(Ball(10.0))
        addFunction(world)

    }
}