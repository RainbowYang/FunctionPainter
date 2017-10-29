package rainbow.component.paint

import rainbow.utils.BufferedImage
import rainbow.utils.screenHeight
import rainbow.utils.screenWidth
import java.awt.image.BufferedImage

/**
 * 实现此接口表示该类能够产生其自身的图片
 *
 * 但绘画的逻辑应通过组合[Painter]来实现
 *
 * @author Rainbow Yang
 */
interface Paintable {

    /**
     * 获得已绘画好的[BufferedImage]
     */
    fun getPaintedImage(width: Number = screenWidth, height: Number = screenHeight): BufferedImage
            = getPaintedImage(width.toInt(), height.toInt())

    fun getPaintedImage(width: Int = screenWidth, height: Int = screenHeight): BufferedImage
}
