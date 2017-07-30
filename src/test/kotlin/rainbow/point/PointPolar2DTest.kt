package rainbow.point

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

import java.lang.Math.PI

class PointPolar2DTest : StringSpec() {

    init {
        "create"{
            PointPolar2D(10, PI)
            PointPolar2D(10.0, PI)

            PointPolar2D(PointForAxes(0, 1)) shouldBe PointPolar2D(1, PI / 2)
        }
        "equal"{
            PointPolar2D(10, PI) shouldBe PointPolar2D(10, PI * 3)
        }
        "toPointForAxes"{
            PointPolar2D(10, 0).toPointForAxes() shouldBe PointForAxes(10, 0)
        }
        "change"{
            PointPolar2D(10, 1) * 2 shouldBe PointPolar2D(20, 1)
            PointPolar2D(10, 2).spin(2) shouldBe PointPolar2D(10, 4)
        }
    }
}