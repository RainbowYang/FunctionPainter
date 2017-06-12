package rainbow.inner.system

import rainbow.inner.painter.SystemPainter
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints

/**
 * 本类用于存贮所有的Painter
 * @author Rainbow Yang
 */
object SystemPainters {
    val painters = mutableListOf<SystemPainter>()

    fun paint(g: Graphics) {
        (g as Graphics2D).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        painters.forEach { it.paint(g) }
    }
}