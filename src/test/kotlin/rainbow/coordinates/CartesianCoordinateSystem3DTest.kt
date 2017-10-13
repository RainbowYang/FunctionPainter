package rainbow.coordinates

import io.kotlintest.specs.StringSpec
import rainbow.point.Point3D
import java.awt.geom.Point2D

/**
 * @author Rainbow Yang
 */
class CartesianCoordinateSystem3DTest : StringSpec() {
    init {
        val system = CartesianCoordinateSystem3D()

        println(system.toScreenPoint(Point3D(3,3,4)))
    }
}