package rainbow.coordinates

import io.kotlintest.specs.StringSpec
import rainbow.point.Point3D
import rainbow.point.Point3DSpherical

/**
 * @author Rainbow Yang
 */
class CartesianCoordinateSystem4DTest : StringSpec() {

    init {

        "create"{
            val system3 = CartesianCoordinateSystem3D()
            val system4 = CartesianCoordinateSystem4D()
            val point = Point3D(100, 351, 0)
            println(point)
            println(system3.toScreenPoint(point))
            println(system4.toScreenPoint(point))
        }
    }

}