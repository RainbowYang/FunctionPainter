package rainbow.inner.painter.function

import rainbow.inner.painter.graphics.MathGraphics
import rainbow.inner.painter.SystemPainter
import rainbow.inner.system.MySystem
import java.awt.Graphics

/**
 * 所有FunctionsPainter的接口
 * 绘画工作一般由MyFunction实现
 * @author Rainbow Yang
 */
interface FunctionsPainter : SystemPainter {
    override fun paint(g: Graphics) {
        MySystem.functions.forEach {
            val mg = MathGraphics(g)
            it.paintBefore(mg)
            it.paintMain(mg)
            it.paintAfter(mg)
        }
    }
}