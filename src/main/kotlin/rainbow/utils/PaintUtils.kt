package rainbow.utils

import rainbow.point.PointDouble
import java.awt.*
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_3BYTE_BGR
import java.awt.image.BufferedImage.TYPE_4BYTE_ABGR

val EMPTY_IMAGE = BufferedImage(1, 1, TYPE_3BYTE_BGR)

fun BufferedImage(width: Int, height: Int) = BufferedImage(width, height, TYPE_4BYTE_ABGR)

val screenWidth = Toolkit.getDefaultToolkit().screenSize.width
val screenHeight = Toolkit.getDefaultToolkit().screenSize.height

/**
 * 抗锯齿
 */
fun Graphics2D.antialias() {
    this.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    this.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
}

fun Graphics2D.drawImage(image: Image) = drawImage(image, 0, 0, null)

//Graphics提供了fillPolygon(Polygon)，却没有drawPolyline(Polygon)，特此扩展
fun Graphics.drawPolyline(p: Polygon) = drawPolyline(p.xpoints, p.ypoints, p.npoints)

fun Polygon.addPoint(it: PointDouble) = addPoint(it.x.toInt(), it.y.toInt())

