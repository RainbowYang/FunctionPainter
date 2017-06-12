package timely.inner.painter.background

import timely.inner.painter.SystemPainter
import timely.inner.system.MySystem
import timely.inner.system.fillRect
import java.awt.Graphics

/**
 * 所有BackgroundPainter的接口
 * @author Rainbow Yang
 */
interface BackgroundPainter : SystemPainter {
    override fun paint(g: Graphics) {
        val bg = MySystem.background

        //画背景色
        g.color = bg.backColor
        MySystem.fillRect(g)

        //画中心图片
        if (bg.img != null) g.drawImage(bg.img, 0, 0, null)

        //画前景色
        if (bg.frontColor != null) {
            g.color = bg.frontColor
            MySystem.fillRect(g)
        }
    }
}