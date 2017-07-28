package rainbow.coordinate.system

import io.kotlintest.specs.StringSpec
import rainbow.coordinates.cartesian.Axis

/**
 * @author Rainbow Yang
 */
class AxesTest : StringSpec() {
    init {
        "create axis"{
            Axis(0.0, 0.0)
        }
    }
}