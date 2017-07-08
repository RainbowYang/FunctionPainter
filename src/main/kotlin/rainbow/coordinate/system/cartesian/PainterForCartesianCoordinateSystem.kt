package rainbow.coordinate.system.cartesian

import rainbow.coordinate.point.PointForAxes
import rainbow.coordinate.graphics.MathGraphics
import rainbow.coordinate.system.CoordinateSystemPainter

/**
 * 默认的Painter For CartesianCoordinateSystem
 * @author Rainbow Yang
 */
class PainterForCartesianCoordinateSystem(val cs: CartesianCoordinateSystem) : CoordinateSystemPainter(cs) {
    override fun paintOrigin(mg: MathGraphics) {
        mg.paintString("O", PointForAxes.ZERO)
    }

    override fun paintGrid(mg: MathGraphics) {
        //维度遍历
        for (s in 0..cs.axes.size - 1) {
            //值遍历
            //todo test
            for (i in -0..49) {
                val p = PointForAxes.ZERO.changeValueAsNew(s, i.toDouble())
                val p1 = p.changeValueAsNew(if (s == cs.axes.size - 1) 0 else s + 1, 1.0)
                val p2 = p.changeValueAsNew(if (s == 0) cs.axes.size - 1 else s - 1, 1.0)
                mg.paintLine(p, p1, MathGraphics.MODE_RAY_LINE)
                mg.paintLine(p, p2, MathGraphics.MODE_RAY_LINE)
            }
        }
    }

    override fun paintAxes(mg: MathGraphics) {
        for (i in 0..cs.axes.size - 1) {
            mg.paintLine(PointForAxes.ZERO, PointForAxes.ZERO.changeValueAsNew(i, 1.0), MathGraphics.MODE_STRAIGHT_LINE)
        }
    }

    override fun paintNum(mg: MathGraphics) {
        //todo Test
        for (s in 0..cs.axes.size - 1) {
            (0..29).filter { it != 0 }.forEach {
                mg.paintString(it.toString() + "", PointForAxes.ZERO.changeValueAsNew(s, it.toDouble()))
            }
        }
    }
}