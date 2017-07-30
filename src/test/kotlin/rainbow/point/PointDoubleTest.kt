package rainbow.point

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class PointDoubleTest : StringSpec() {
    init {
        "create and equal"{
            PointDouble(1.0, 1.0) shouldBe PointDouble(1, 1)
            PointDouble(1.0, 1.0) shouldBe PointDouble(PointForAxes(1, 1))
        }
        "change"{
            PointDouble(1, 1) + PointDouble(1, 1) shouldBe PointDouble(2, 2)
            PointDouble(1, 1) * 2 shouldBe PointDouble(2, 2)
            PointDouble(1, 1).spin(Math.PI / 2) shouldBe PointDouble(-1, 1)
        }
        "available"{
            PointDouble(Double.NaN, Double.NaN).available shouldBe false
        }
        "length and angle"{
            PointDouble(3, 4).length shouldBe 5.0
            PointDouble(1, 0).angle shouldBe 0.0
        }
    }
}