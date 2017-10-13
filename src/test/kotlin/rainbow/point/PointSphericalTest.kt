package rainbow.point

import io.kotlintest.specs.StringSpec
import rainbow.utils.asPointSpherical

/**
 * @author Rainbow Yang
 */
class PointSphericalTest : StringSpec() {
    init {
        "create"{
            val pa = PointAxes(1, 1, 1, 1, 1)
            val ps = pa.asPointSpherical
            println(ps)
            println(ps.asAxes)
        }
    }
}