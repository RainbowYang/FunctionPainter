package rainbow.inner.painter.coordinate_system

import rainbow.inner.painter.graphics.MyGraphics
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
    fun paintOrigin(mg: MyGraphics) {}

    /**
     * 画网格

     * @param mg 画笔
     */
    fun paintGrid(mg: MyGraphics) {}

    /**
     * 画坐标轴

     * @param mg 画笔
     */
    fun paintAxes(mg: MyGraphics) {}

    /**
     * 画数字

     * @param mg 画笔
     */
    fun paintNum(mg: MyGraphics) {}

    override fun paint(g: Graphics) {
        val mg = MyGraphics(g)
        paintOrigin(mg)
        paintGrid(mg)
        paintAxes(mg)
        paintNum(mg)
    }
}
