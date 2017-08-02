package rainbow.point

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class Point2DTest : StringSpec() {
    init {
        "create and equal"{
            Point2D(1.0, 1.0) shouldBe Point2D(1, 1)
            Point2D(1.0, 1.0) shouldBe Point2D(PointAxes(1, 1))
        }
        "change"{
            Point2D(1, 1) + Point2D(1, 1) shouldBe Point2D(2, 2)
            Point2D(1, 1) * 2 shouldBe Point2D(2, 2)
            Point2D(1, 1).spin(Math.PI / 2) shouldBe Point2D(-1, 1)
        }
        "available"{
            Point2D(Double.NaN, Double.NaN).available shouldBe false
        }
        "length and angle"{
            Point2D(3, 4).length shouldBe 5.0
            Point2D(1, 0).angle shouldBe 0.0
        }
    }
}