package rainbow.coordinates

import io.kotlintest.specs.StringSpec
import rainbow.point.Point3D
import rainbow.point.PointAxes

/**
 * @author Rainbow Yang
 */
class CartesianCoordinateSystem4DTest : StringSpec() {

    init {

        "create"{
            val system4 = CartesianCoordinateSystem4D()
            val point = Point3D(100, 100, 0)
            println(system4.toScreenPoint(point))
            system4.towards += PointAxes(0, 0, 0, 1000)
            println(system4.toScreenPoint(point))
        }
    }

}