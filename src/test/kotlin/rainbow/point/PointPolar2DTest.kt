package rainbow.point

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class PointPolar2DTest : StringSpec() {

    init {
        "create"{
            PointPolar2D(10, Math.PI)
            PointPolar2D(10.0, Math.PI)
        }
        "equal"{
            PointPolar2D(10, Math.PI) shouldBe PointPolar2D(10, Math.PI * 3)
        }
        "toPointForAxes"{
            PointPolar2D(10, 0).toPointForAxes() shouldBe PointForAxes(10, 0)
        }
        "change"{
            PointPolar2D(10, 1) * 2 shouldBe PointPolar2D(20, 1)
        }
    }
}