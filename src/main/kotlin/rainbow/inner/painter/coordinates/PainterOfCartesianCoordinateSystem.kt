package rainbow.inner.painter.coordinates

import rainbow.inner.coordinate.point.PointForAxes
import rainbow.inner.coordinate.system.cartesian.CartesianCoordinateSystem
import rainbow.inner.coordinate.system.graphics.MathGraphics

/**
 * @author Rainbow Yang
 */
class PainterOfCartesianCoordinateSystem(val cs: CartesianCoordinateSystem) : CoordinateSystemPainter(cs) {
    override fun paintOrigin(mg: MathGraphics) {
        val p0 = PointForAxes(0.0, cs.axes.size)
        mg.paintString("O", p0)
    }

    override fun paintGrid(mg: MathGraphics) {
        val p0 = PointForAxes(0.0, cs.axes.size)
        //维度遍历
        for (s in 0..cs.axes.size - 1) {
            //值遍历
            //todo test
            for (i in -0..49) {
                val p = p0.changeValueAsNew(s, i.toDouble())
                val p1 = p.changeValueAsNew(if (s == cs.axes.size - 1) 0 else s + 1, 1.0)
                val p2 = p.changeValueAsNew(if (s == 0) cs.axes.size - 1 else s - 1, 1.0)
                mg.paintLine(p, p1, MathGraphics.MODE_RAY_LINE)
                mg.paintLine(p, p2, MathGraphics.MODE_RAY_LINE)
            }
        }
    }

    override fun paintAxes(mg: MathGraphics) {
        val p0 = PointForAxes(0.0, cs.axes.size)
        for (i in 0..cs.axes.size - 1) {
            mg.paintLine(p0, p0.changeValueAsNew(i, 1.0), MathGraphics.MODE_STRAIGHT_LINE)
        }
    }

    override fun paintNum(mg: MathGraphics) {
        //todo Test
        val p0 = PointForAxes(0.0, cs.axes.size)
        for (s in 0..cs.axes.size - 1) {
            for (i in -0..29) {
                if (i == 0) continue
                mg.paintString(i.toString() + "", p0.changeValueAsNew(s, i.toDouble()))
            }
        }
    }
}