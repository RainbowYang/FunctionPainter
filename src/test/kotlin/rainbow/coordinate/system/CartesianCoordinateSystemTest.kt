package rainbow.coordinate.system

import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.StringSpec
import rainbow.coordinates.CartesianCoordinateSystem

/**
 * @author Rainbow Yang
 */
class CartesianCoordinateSystemTest : StringSpec() {
    init {
        "create coordinate system"{
            shouldThrow<IllegalArgumentException> {
                (0..1).forEach {
                    println(CartesianCoordinateSystem(it))
                }
            }

            (2..10).forEach {
                println(CartesianCoordinateSystem(it))
            }
        }
    }
}