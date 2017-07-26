package rainbow.ables

import java.awt.Graphics
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_4BYTE_ABGR

/**
 * 实现此类后，应该创建Painter的子类并对painter进行初始化
 * @author Rainbow Yang
 */
interface Paintable {

    var painter: Painter

    /**
     * 将当前已绘制好的图像画到graphics上
     */
    fun paintImage(g: Graphics) {
        g.drawImage(painter.cacheImage, 0, 0, null)
    }

    /**
     * 重新绘画
     */
    fun repaint(width: Number, height: Number, callback: () -> Unit) = painter.repaint(width, height, callback)
}

abstract class Painter {

    var cacheImage: BufferedImage = EmptyImage
    lateinit protected var paintingImage: BufferedImage

    var _callback = {}

    /**
     * 在绘画期间可以决定：
     * 要不要将[cacheImage]清空
     * 要不要调用[callback]去显示尚未完成的图像
     */
    fun repaint(width: Number, height: Number, callback: () -> Unit) {
        this._callback = callback
        paintingImage = newImage(width, height)

        repaint()
    }

    open fun repaint() {}

    fun callback() {
        cacheImage = newImage(paintingImage).apply {
            graphics.drawImage(paintingImage, 0, 0, null)
        }
        _callback()
    }
}

object EmptyPainter : Painter()

object EmptyImage : BufferedImage(1, 1, TYPE_4BYTE_ABGR)

fun newImage(width: Number, height: Number) = BufferedImage(width.toInt(), height.toInt(), TYPE_4BYTE_ABGR)
fun newImage(image: BufferedImage) = BufferedImage(image.width, image.height, TYPE_4BYTE_ABGR)
