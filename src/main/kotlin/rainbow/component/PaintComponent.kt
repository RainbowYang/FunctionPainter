package rainbow.component

import rainbow.utils.EMPTY_IMAGE
import java.awt.Graphics2D
import java.awt.image.BufferedImage

/**
 * 绘画组件
 *
 * @author Rainbow Yang
 */
abstract class PaintComponent {

    /**
     * 将图片绘画到[graphics]上
     */
    abstract fun paintImageTo(graphics: Graphics2D, width: Int = 1920, height: Int = 1080)


    /**
     * 获得已绘画好的[BufferedImage]
     */
    abstract fun paintedImage(width: Int = 1920, height: Int = 1080): BufferedImage
}

fun EmptyPaintComponent() = object : PaintComponent() {

    override fun paintImageTo(graphics: Graphics2D, width: Int, height: Int) {}

    override fun paintedImage(width: Int, height: Int): BufferedImage = EMPTY_IMAGE

}

