package rainbow.coordinates

import rainbow.point.PointAxes
import rainbow.utils.CoordinateGraphics
import rainbow.utils.rangeTo

/**
 * @author Rainbow Yang
 */
open class PaintComponentOfCartesianCoordinateSystem(val system: CartesianCoordinateSystem) :
        PaintComponentOfCoordinateSystem(system) {

    var paintRange = 0..30
    val sizeRange get() = rangeTo(system.size)

    init {
        addPaintPart(ORIGIN) { paintOrigin(it) }
        addPaintPart(GRID) { paintGrid(it) }
        addPaintPart(AXES) { paintAxes(it) }
        addPaintPart(NUMBER) { paintNumber(it) }
    }

    fun paintOrigin(cg: CoordinateGraphics) = cg.paintString("O", PointAxes.ZERO)

    fun paintGrid(cg: CoordinateGraphics) = with(system) {
        //维度遍历
        for (i in sizeRange) {
            //值遍历
            for (value in paintRange) {
                val p0 = PointAxes.ZERO.plusAtAndNew(i, value)
                val p1 = p0.plusAtAndNew(if (i == axes.size - 1) 0 else i + 1, 1.0)
                val p2 = p0.plusAtAndNew(if (i == 0) axes.size - 1 else i - 1, 1.0)

                cg.paintRayLine(p0, p1)
                cg.paintRayLine(p0, p2)
            }
        }
    }

    fun paintAxes(cg: CoordinateGraphics) = with(system) {
        sizeRange.forEach {
            cg.paintStraightLine(PointAxes.ZERO, PointAxes.ZERO.plusAtAndNew(it, 10))
        }
    }

    fun paintNumber(cg: CoordinateGraphics) = with(system) {
        sizeRange.forEach { size ->
            paintRange.filter { it != 0 }.forEach {
                cg.paintString(it, PointAxes.ZERO.plusAtAndNew(size, it))
            }
        }
    }
}
