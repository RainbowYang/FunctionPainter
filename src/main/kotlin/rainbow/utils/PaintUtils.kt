package rainbow.utils

import rainbow.component.Paintable
import rainbow.coordinates.CoordinateSystem
import rainbow.point.Point2D
import java.awt.*
import java.awt.Color.BLACK
import java.awt.RenderingHints.*
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_3BYTE_BGR
import java.awt.image.BufferedImage.TYPE_4BYTE_ABGR

val DEFAULT_COLOR = BLACK

val EMPTY_IMAGE = BufferedImage(1, 1, TYPE_3BYTE_BGR)

fun BufferedImage(width: Int, height: Int) = BufferedImage(width, height, TYPE_4BYTE_ABGR)

val screenWidth = Toolkit.getDefaultToolkit().screenSize.width
val screenHeight = Toolkit.getDefaultToolkit().screenSize.height

/**
 * 抗锯齿
 */
fun Graphics2D.antialias() {
    this.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
    this.setRenderingHint(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);
}

fun Graphics2D.drawImage(image: Image) = drawImage(image, 0, 0, null)

fun Graphics2D.drawImageOfPainter(painter: Paintable, width: Number, height: Number)
        = drawImage(painter.paintedImage(width, height))

fun Graphics2D.drawImageOfPainter(painter: Paintable, size: Dimension)
        = drawImage(painter.paintedImage(size.width, size.height))

fun Graphics2D.with(coordinateSystem: CoordinateSystem) = CoordinateGraphics(this, coordinateSystem)

//Graphics提供了fillPolygon(Polygon)，却没有drawPolyline(Polygon)，特此扩展
fun Graphics.drawPolyline(p: Polygon) = drawPolyline(p.xpoints, p.ypoints, p.npoints)

fun Polygon.addPoint(it: Point2D) = addPoint(it.x.toInt(), it.y.toInt())

fun parseColor(str: String) = Color.getColor(null, Integer.decode(str)) ?: BLACK
