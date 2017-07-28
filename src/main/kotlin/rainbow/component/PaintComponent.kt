package rainbow.component

import rainbow.utils.drawImage
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
    open fun paintImageTo(graphics: Graphics2D, width: Int = 1920, height: Int = 1080) {
        graphics.drawImage(paintedImage(width, height))
    }

    /**
     * 获得已绘画好的[BufferedImage]
     */
    abstract fun paintedImage(width: Int = 1920, height: Int = 1080): BufferedImage
}
