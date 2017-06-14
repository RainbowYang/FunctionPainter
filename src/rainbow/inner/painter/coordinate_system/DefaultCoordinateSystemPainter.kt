package rainbow.inner.painter.coordinate_system

import com.sun.awt.SecurityWarning.getSize
import rainbow.inner.coordinate.point.PointForAxes
import rainbow.inner.coordinate.system.CoordinateSystem
import rainbow.inner.painter.graphics.MathGraphics
import rainbow.inner.system.MySystem
import java.awt.Color

/**
 * @author Rainbow Yang
 */
class DefaultCoordinateSystemPainter : CoordinateSystemPainter {

    override fun paintNum(mg: MathGraphics) {
        val cs = MySystem.coordinateSystem
        mg.setColor(cs.colors.getColor("colorOfNum"))
        //todo Test
        val p0 = PointForAxes(0.0, cs.axes.getSize(), true)
        for (s in 0..cs.axes.getSize() - 1) {
            for (i in -0..29) {
                if (i == 0) continue
                mg.paintString(i.toString() + "", p0.add(s, i.toDouble()))
            }
        }
    }

    override fun paintOrigin(mg: MathGraphics) {
        val cs = MySystem.coordinateSystem
        mg.setColor(cs.colors.getColor("colorOfOrigin"))

        val p0 = PointForAxes(0.0, cs.axes.getSize(), true)
        mg.paintString("O", p0)
    }

    override fun paintAxes(mg: MathGraphics) {
        val cs = MySystem.coordinateSystem
        mg.setColor(cs.colors.getColor("colorOfAxes"))

        val p0 = PointForAxes(0.0, cs.axes.getSize(), true)
        mg.setColor(Color.WHITE)
        for (i in 0..cs.axes.getSize() - 1) {
            mg.paintLine(p0, p0.add(i, 1.0), MathGraphics.MODE_STRAIGHT_LINE)
        }
    }

    override fun paintGrid(mg: MathGraphics) {
        val cs = MySystem.coordinateSystem
        mg.setColor(cs.colors.getColor("colorOfGrid"))

        val p0 = PointForAxes(0.0, cs.axes.getSize(), true)
        //维度遍历
        for (s in 0..cs.axes.getSize() - 1) {
            //值遍历
            //todo test
            for (i in -0..49) {
                val p = p0.add(s, i.toDouble())
                val p1 = p.add(if (s == cs.axes.getSize() - 1) 0 else s + 1, 1.0)
                val p2 = p.add(if (s == 0) cs.axes.getSize() - 1 else s - 1, 1.0)
                mg.paintLine(p, p1, MathGraphics.MODE_RAY_LINE)
                mg.paintLine(p, p2, MathGraphics.MODE_RAY_LINE)
            }
        }
    }
}