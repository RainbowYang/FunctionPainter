package timely.inner.painter.function

import rainbow.inner.view.graphics.MyGraphics
import timely.inner.painter.SystemPainter
import timely.inner.system.MySystem
import java.awt.Graphics

/**
 * 所有FunctionsPainter的接口
 * 绘画工作一般由MyFunction实现
 * @author Rainbow Yang
 */
interface FunctionsPainter : SystemPainter {
    override fun paint(g: Graphics) {
        MySystem.functions.forEach {
            val mg = MyGraphics(g)
            it.paintBefore(mg)
            it.paintMain(mg)
            it.paintAfter(mg)
        }
    }
}