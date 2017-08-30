package rainbow.component

import rainbow.utils.BufferedImage
import rainbow.utils.parseColor
import rainbow.utils.screenHeight
import rainbow.utils.screenWidth
import java.awt.Graphics2D
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
    fun paintedImage(width: Number = screenWidth, height: Number = screenHeight): BufferedImage
            = paintedImage(width.toInt(), height.toInt())

    fun paintedImage(width: Int = screenWidth, height: Int = screenHeight): BufferedImage
}

/**
 * 用于组合的绘画组件
 * @author Rainbow Yang
 */
abstract class Painter(var visible: Boolean = true) : Paintable {

    val paintParts = mutableListOf<PaintPart>()

    override fun paintedImage(width: Int, height: Int) = paintImage(width, height)

    var width: Int = screenWidth
        private set

    var height: Int = screenHeight
        private set

    /**
     * 对图片进行绘画
     *
     * 由使用者确定
     * 调用其接口的[paintedImage]时，直接使用该方法进行实时绘画(默认) 或 使用缓存来避免线程阻碍
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

    open fun addPaintPart(name: String,
                          color: String = "#000000",
                          visible: Boolean = true,
                          paint: (Graphics2D) -> Unit = {}) =
            paintParts.add(PaintPart(name, color, visible, paint))

    operator fun String.invoke(color: String = "#000000",
                               visible: Boolean = true,
                               paint: (Graphics2D) -> Unit) =
            addPaintPart(this, color, visible, paint)

    open class PaintPart(var name: String, var color: String, var visible: Boolean, var paint: (Graphics2D) -> Unit)

}