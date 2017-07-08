package rainbow.inner.background

import rainbow.inner.painter.SystemPainter
import java.awt.Color
import java.awt.Graphics
import java.awt.Image

import rainbow.tools.getColorByHexRGB

/**
 * 背景
 *
 * 分为三层
 * 背景色，中心图片，前景色
 * 三者依次画出
 *
 * @author Rainbow Yang
 */
open class Background(var backColor: Color? = Color.WHITE, var img: Image? = null, var frontColor: Color? = null) : SystemPainter {

    constructor(backColor: String, img: Image? = null, frontColor: String? = null) :
            this(getColorByHexRGB(backColor), img, getColorByHexRGB(frontColor))


    override fun paint(g: Graphics, width: Double, height: Double) {
        if (backColor != null)
            paintBack(g, width.toInt(), height.toInt())

        if (img != null)
            paintImage(g, width.toInt(), height.toInt())

        if (frontColor != null)
            paintFront(g, width.toInt(), height.toInt())
    }

    fun paintBack(g: Graphics, width: Int, height: Int) {
        g.color = backColor
        g.fillRect(0, 0, width, height)
    }

    fun paintImage(g: Graphics, width: Int, height: Int) {
        g.drawImage(img, 0, 0, null)
    }

    fun paintFront(g: Graphics, width: Int, height: Int) {
        g.color = frontColor
        g.fillRect(0, 0, width, height)
    }

    override fun toString(): String {
        return "Background(backColor=$backColor, img=$img, frontColor=$frontColor)"
    }

    val Image.width: Int
        get() = this.getWidth(null)
    val Image.height: Int
        get() = this.getHeight(null)

}