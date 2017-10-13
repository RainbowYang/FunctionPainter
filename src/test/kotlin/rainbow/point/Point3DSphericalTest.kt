package rainbow.point

import io.kotlintest.Spec
import io.kotlintest.specs.StringSpec

/**
 * @author Rainbow Yang
 */
class Point3DSphericalTest() : StringSpec() {
    init {
        "create"{
            val point = Point3D(1, 1, 1)
            val sPoint = Point3DSpherical(point)
            println(sPoint)
            println(sPoint.asAxes)
        }
    }
}