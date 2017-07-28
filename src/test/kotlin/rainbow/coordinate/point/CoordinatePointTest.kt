package rainbow.coordinate.point

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import rainbow.point.PointForAxes

/**
 * @author Rainbow Yang
 */
class CoordinatePointTest : StringSpec() {
    init {
        "for PointForAxes"{
            var point: PointForAxes = PointForAxes.ZERO

            point.plusAtAndNew(1, 1.0) shouldBe PointForAxes(0, 1)
            point.timesAtAndNew(1, 1.0) shouldBe PointForAxes(0)
        }
    }
}
