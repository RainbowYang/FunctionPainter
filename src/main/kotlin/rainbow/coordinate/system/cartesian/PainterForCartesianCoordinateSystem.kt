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

    override fun paintOrigin(mg: CoordinateGraphics) {
        mg.color = colorOfOrigin
        mg.paintString("O", PointForAxes.ZERO)
    }

    override fun paintGrid(mg: CoordinateGraphics) {
        mg.color = colorOfGrid

        //维度遍历
        for (index in 0..ccs.axes.size - 1) {
            //值遍历
            for (value in 0..30) {
                val p0 = PointForAxes.ZERO.plusAtAndNew(index, value)
                val p1 = p0.plusAtAndNew(if (index == ccs.axes.size - 1) 0 else index + 1, 1.0)
                //  val p2 = p.plusAtAndNew(if (index == 0) ccs.axes.size - 1 else s - 1, 1.0)

                mg.paintRayLine(p0, p1)
            }
        }
    }

    override fun paintAxes(mg: CoordinateGraphics) {
        mg.color = colorOfAxes

        for (index in 0..ccs.axes.size - 1) {
            mg.paintStraightLine(PointForAxes.ZERO, PointForAxes.ZERO.plusAtAndNew(index, 10))
        }
    }

    override fun paintNumber(mg: CoordinateGraphics) {
        mg.color = colorOfNumber

        for (index in 0..ccs.axes.size - 1) {
            (0..29).filter { it != 0 }.forEach {
                mg.paintString(it, PointForAxes.ZERO.plusAtAndNew(index, it))
            }
        }
    }
}