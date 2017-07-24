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
    fun paintImage(g: Graphics) = g.drawImage(painter.cacheImage, 0, 0, null)

    /**
     * 重新绘画
     */
    fun repaint(width: Number, height: Number, callback: () -> Unit) = painter.repaint(width, height, callback)
}

abstract class Painter {

    var cacheImage: BufferedImage = EmptyImage

    var width = 0.0
    var height = 0.0

    var callback = {}

    /**
     * 在绘画期间可以决定：
     * 要不要将[cacheImage]清空
     * 要不要调用[callback]去显示尚未完成的图像
     */
    fun repaint(width: Number, height: Number, callback: () -> Unit) {
        this.width = width.toDouble()
        this.height = height.toDouble()
        this.callback = callback

        Thread { repaint() }.start()
    }

    open fun repaint() {}

    protected fun cleanImage() {
        cacheImage = EmptyImage
    }
}

object EmptyPainter : Painter()

object EmptyImage : BufferedImage(1, 1, TYPE_4BYTE_ABGR)

fun newImage(width: Number, height: Number) = BufferedImage(width.toInt(), height.toInt(), TYPE_4BYTE_ABGR)
