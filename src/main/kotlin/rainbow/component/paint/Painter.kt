package rainbow.component.paint

import rainbow.utils.BufferedImage
import rainbow.utils.parseColor
import rainbow.utils.screenHeight
import rainbow.utils.screenWidth
import java.awt.Graphics2D
import java.awt.image.BufferedImage

/**
 * 用于组合的绘画组件
 * @author Rainbow Yang
 */
abstract class Painter(var visible: Boolean = true) : Paintable {

    val paintParts = mutableListOf<PaintPart>()

    override fun getPaintedImage(width: Int, height: Int) = paintImage(width, height)

    var width: Int = screenWidth
        private set

    var height: Int = screenHeight
        private set

    /**
     * 对图片进行绘画
     *
     * 由使用者确定
     * 调用其接口的[getPaintedImage]时，直接使用该方法进行实时绘画(默认) 或 使用缓存来避免线程阻碍
     */
    open fun paintImage(width: Int, height: Int): BufferedImage {

        this.width = width
        this.height = height

        return BufferedImage(width, height).also {
            if (visible) {
                val graphics = it.createGraphics()

                paintParts.filter { it.visible }.forEach {
                    val color = graphics.color

                    graphics.color = parseColor(it.color)
                    it.paint(graphics)

                    graphics.color = color
                }
            }
        }
    }

    fun addPaintPart(name: String,
                     color: String = "#000000",
                     visible: Boolean = true,
                     paint: (Graphics2D) -> Unit = {}) {
        paintParts.add(PaintPart(name, color, visible, paint))
    }

    open class PaintPart(var name: String, var color: String, var visible: Boolean, var paint: (Graphics2D) -> Unit)

}