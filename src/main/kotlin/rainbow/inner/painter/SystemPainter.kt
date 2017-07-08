package rainbow.inner.painter

import java.awt.Graphics

/**
 * 系统画笔
 * @author Rainbow Yang
 */
interface SystemPainter {
    fun paint(g: Graphics, width: Double, height: Double)
    fun paint(g: Graphics, width: Number, height: Number) = paint(g, width.toDouble(), height.toDouble())
}