package rainbow.point

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

import rainbow.point.PointForAxes.Companion.ZERO

class PointForAxesTest : StringSpec() {
    init {

        "create"{
            PointForAxes(listOf(1, 1))
        }

        "equal"{
            PointForAxes(0) shouldBe ZERO
            PointForAxes(0.0, 0) shouldBe ZERO

            PointForAxes(1, 1) shouldBe PointForAxes(1, 1, 0)
        }
        "toPointForAxes"{
            ZERO.toPointForAxes() shouldBe PointForAxes.ZERO
        }

        "change"{

            ZERO.plusAtAndNew(1, 1) shouldBe PointForAxes(0, 1)
            ZERO.setAtAndNew(1, 1) shouldBe PointForAxes(0, 1)
            ZERO.timesAtAndNew(1, 1) shouldBe PointForAxes(0, 0)

            PointForAxes(1, 1, 1) * 2 shouldBe PointForAxes(2, 2, 2)
        }
    }
}