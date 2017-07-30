package rainbow.point

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

import java.lang.Math.PI

class Point2DPolarTest : StringSpec() {

    init {
        "create"{
            Point2DPolar(10, PI)
            Point2DPolar(10.0, PI)

            Point2DPolar(PointForAxes(0, 1)) shouldBe Point2DPolar(1, PI / 2)
        }
        "equal"{
            Point2DPolar(10, PI) shouldBe Point2DPolar(10, PI * 3)
        }
        "toPointForAxes"{
            Point2DPolar(10, 0).toPointForAxes() shouldBe PointForAxes(10, 0)
        }
        "change"{
            Point2DPolar(10, 1) * 2 shouldBe Point2DPolar(20, 1)
            Point2DPolar(10, 2).spin(2) shouldBe Point2DPolar(10, 4)
        }
    }
}