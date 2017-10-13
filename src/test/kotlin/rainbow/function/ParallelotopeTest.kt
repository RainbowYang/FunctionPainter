package rainbow.function

import io.kotlintest.specs.StringSpec
import rainbow.point.Point2D
import rainbow.point.Point3D
import rainbow.point.PointAxes

/**
 * @author Rainbow Yang
 */
class ParallelotopeTest : StringSpec() {
    init {
        val p = Parallelotope(PointAxes.ZERO, Point2D(0, 1), Point2D(1, 0), Point3D(0, 0, 1))

    }
}