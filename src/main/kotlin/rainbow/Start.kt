package rainbow

import rainbow.controller.SystemController
import rainbow.coordinates.CartesianCoordinateSystem3D
import rainbow.function.mathfunction.special.Grid
import rainbow.function.space.body.Ball
import rainbow.function.space.world.World
import rainbow.point.Point3D
import rainbow.utils.G

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


        val world = World()
        val ball = Ball(3.0, location = Point3D(-10, 0, 0),
                velocity = Point3D(0, 10, 0), mass = 4000 / G)
        val ball2 = Ball(1.0, location = Point3D(10, 0, 0),
                velocity = Point3D(0, -10, 0), mass = 4000 / G)

        world.addBody(ball)
        world.addBody(ball2)

        addFunction(world)

        addFunction(world.getGrid())

    }
}