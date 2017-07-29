package rainbow.component

import rainbow.utils.drawImage
import rainbow.utils.screenHeight
import rainbow.utils.screenWidth
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
    open fun paintImageTo(graphics: Graphics2D, width: Int = screenWidth, height: Int = screenHeight) {
        graphics.drawImage(paintedImage(width, height))
    }

    /**
     * 获得已绘画好的[BufferedImage]
     */
    abstract fun paintedImage(width: Int = screenWidth, height: Int = screenHeight): BufferedImage
}
