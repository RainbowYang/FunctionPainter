package rainbow.inner.painter.coordinate_system

import rainbow.inner.painter.graphics.MathGraphics
import rainbow.inner.painter.SystemPainter
import java.awt.Graphics

/**
 * 所有CoordinateSystemPainter的接口
 * @author Rainbow Yang
 */
interface CoordinateSystemPainter : SystemPainter {
    /**
     * 画原点

     * @param mg 画笔
     */
    fun paintOrigin(mg: MathGraphics) {}

    /**
     * 画网格

     * @param mg 画笔
     */
    fun paintGrid(mg: MathGraphics) {}

    /**
     * 画坐标轴

     * @param mg 画笔
     */
    fun paintAxes(mg: MathGraphics) {}

    /**
     * 画数字

     * @param mg 画笔
     */
    fun paintNum(mg: MathGraphics) {}

    override fun paint(g: Graphics) {
        val mg = MathGraphics(g)
        paintOrigin(mg)
        paintGrid(mg)
        paintAxes(mg)
        paintNum(mg)
    }
}
