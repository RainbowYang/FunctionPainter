package rainbow.inner.coordinate.system.comp.painter

import rainbow.inner.coordinate.system.CoordinateSystem
import rainbow.inner.coordinate.system.graphics.MathGraphics
import rainbow.inner.painter.SystemPainter
import java.awt.Color
import java.awt.Graphics

/**
 * 所有CoordinateSystemPainter的接口
 * @author Rainbow Yang
 */
abstract class CoordinateSystemPainter(val coordinateSystem: CoordinateSystem) : SystemPainter {
    /**
     * 画原点

     * @param mg 画笔
     */
    open fun paintOrigin(mg: MathGraphics) {}

    /**
     * 画网格

     * @param mg 画笔
     */
    open fun paintGrid(mg: MathGraphics) {}

    /**
     * 画坐标轴

     * @param mg 画笔
     */
    open fun paintAxes(mg: MathGraphics) {}

    /**
     * 画数字

     * @param mg 画笔
     */
    open fun paintNum(mg: MathGraphics) {}

    override fun paint(g: Graphics) {
        val mg = MathGraphics(g, coordinateSystem)
        paintOrigin(mg)
        paintGrid(mg)
        paintAxes(mg)
        paintNum(mg)
    }
}
