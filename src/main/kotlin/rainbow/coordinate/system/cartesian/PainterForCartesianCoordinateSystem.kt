package rainbow.coordinate.system.cartesian

import rainbow.coordinate.graphics.CoordinateGraphics
import rainbow.coordinate.point.PointForAxes
import rainbow.coordinate.system.CoordinateSystemPainter
import java.awt.Color

/**
 * 默认的Painter For CartesianCoordinateSystem
 * @author Rainbow Yang
 */
class PainterForCartesianCoordinateSystem(val ccs: CartesianCoordinateSystem) : CoordinateSystemPainter(ccs) {

    var colorOfOrigin: Color = Color.BLACK
    var colorOfGrid: Color = Color.BLACK
    var colorOfAxes: Color = Color.BLACK
    var colorOfNumber: Color = Color.BLACK

    override fun paintOrigin(cg: CoordinateGraphics) {
        cg.color = colorOfOrigin
        cg.paintString("O", PointForAxes.ZERO)
    }

    override fun paintGrid(cg: CoordinateGraphics) {
        cg.color = colorOfGrid

        //维度遍历
        for (index in 0..ccs.axes.size - 1) {
            //值遍历
            for (value in 0..30) {
                val p0 = PointForAxes.ZERO.plusAtAndNew(index, value)
                val p1 = p0.plusAtAndNew(if (index == ccs.axes.size - 1) 0 else index + 1, 1.0)
                val p2 = p0.plusAtAndNew(if (index == 0) ccs.axes.size - 1 else index - 1, 1.0)

                cg.paintRayLine(p0, p1)
                cg.paintRayLine(p0, p2)
            }
        }
    }

    override fun paintAxes(cg: CoordinateGraphics) {
        cg.color = colorOfAxes

        for (index in 0..ccs.axes.size - 1) {
            cg.paintStraightLine(PointForAxes.ZERO, PointForAxes.ZERO.plusAtAndNew(index, 10))
        }
    }

    override fun paintNumber(cg: CoordinateGraphics) {
        cg.color = colorOfNumber

        for (index in 0..ccs.axes.size - 1) {
            (0..29).filter { it != 0 }.forEach {
                cg.paintString(it, PointForAxes.ZERO.plusAtAndNew(index, it))
            }
        }
    }
}