package rainbow.point

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

import rainbow.point.PointAxes.Companion.ZERO

class PointAxesTest : StringSpec() {
    init {

        "create"{
            PointAxes(listOf(1, 1)) shouldBe PointAxes(1, 1)
        }

        "equal"{
            PointAxes(0) shouldBe ZERO
            PointAxes(0.0, 0) shouldBe ZERO

            PointAxes(1, 1) shouldBe PointAxes(1, 1, 0)
        }
        "toPointForAxes"{
            ZERO.toPointForAxes() shouldBe PointAxes.ZERO
        }

        "change"{

            ZERO.plusAtAndNew(1, 1) shouldBe PointAxes(0, 1)
            ZERO.setAtAndNew(1, 1) shouldBe PointAxes(0, 1)
            ZERO.timesAtAndNew(1, 1) shouldBe PointAxes(0, 0)

            PointAxes(1, 1, 1) * 2 shouldBe PointAxes(2, 2, 2)
        }
    }
}