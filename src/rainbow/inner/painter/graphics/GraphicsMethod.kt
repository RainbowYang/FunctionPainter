package rainbow.inner.painter.graphics

import java.awt.Graphics
import java.awt.Polygon

/**
 * Graphics扩展
 * @author Rainbow Yang
 */

//Graphics提供了fillPolygon(Polygon)，却没有drawPolyline(Polygon)，特此扩展
fun Graphics.drawPolyline(p: Polygon) = this.drawPolyline(p.xpoints, p.ypoints, p.npoints)
