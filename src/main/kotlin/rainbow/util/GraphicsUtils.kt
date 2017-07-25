package rainbow.util

import rainbow.coordinate.point.PointDouble
import java.awt.Graphics
import java.awt.Polygon

/**
 * @author Rainbow Yang
 */

//Graphics提供了fillPolygon(Polygon)，却没有drawPolyline(Polygon)，特此扩展
fun Graphics.drawPolyline(p: Polygon) = drawPolyline(p.xpoints, p.ypoints, p.npoints)

fun Polygon.addPoint(it: PointDouble) = addPoint(it.x.toInt(), it.y.toInt())