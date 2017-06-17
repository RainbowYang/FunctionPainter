package rainbow.inner.background

import java.awt.Color
import java.awt.Color.getColor
import java.awt.Graphics
import java.awt.Image

/**
 * 背景
 *
 * 分为三层
 * 背景色，中心图片，前景色
 * 三者依次画出
 *
 * @author Rainbow Yang
 */
open class Background(var backColor: Color = Color.WHITE, var img: Image? = null, var frontColor: Color? = null) {

    constructor(backColor: String, img: Image? = null, frontColor: String? = null) :
            this(getColor(backColor), img, if (frontColor == null) null else getColor(frontColor))

    fun paint(g: Graphics, width: Int, height: Int) {
        paintBack(g, width, height)

        if (img != null)
            paintImage(g)

        if (frontColor != null)
            paintFront(g, width, height)
    }

    fun paintBack(g: Graphics, width: Int, height: Int) {
        g.color = backColor
        g.fillRect(0, 0, width, height)
    }

    fun paintImage(g: Graphics) {
        g.drawImage(img, 0, 0, null)
    }

    fun paintFront(g: Graphics, width: Int, height: Int) {
        g.color = frontColor
        g.fillRect(0, 0, width, height)
    }
}