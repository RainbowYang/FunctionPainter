package timely.inner.painter

import java.awt.Graphics

/**
 * 系统画笔
 * 所有要画在界面上的东西都应该要继承此类
 * @author Rainbow Yang
 */
interface SystemPainter {
    fun paint(g: Graphics)
}