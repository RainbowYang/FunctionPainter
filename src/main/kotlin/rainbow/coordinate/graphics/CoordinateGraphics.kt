package rainbow.coordinate.graphics

import rainbow.coordinate.point.CoordinatePoint
import rainbow.coordinate.point.PointDouble
import rainbow.coordinate.system.CoordinateSystem
import rainbow.inner.math.Line
import java.awt.*
import java.awt.image.BufferedImage

/**
 * 坐标画笔，对Graphics进行包装
 * 可以直接用CoordinatePoint进行操作
 *
 * @see CoordinatePoint
 * @author Rainbow Yang
 */
class CoordinateGraphics(val g: Graphics,
                         val system: CoordinateSystem,
                         val width: Double = 1920.0,
                         val height: Double = 1080.0) {

    constructor(g: Graphics,
                system: CoordinateSystem,
                width: Number = 1920.0,
                height: Number = 1080.0) : this(g, system, width.toDouble(), height.toDouble())

    constructor(image: BufferedImage,
                system: CoordinateSystem) : this(image.graphics, system, image.width, image.height)

    init {
        (g as Graphics2D).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    }

    var color: Color
        get () = g.color
        set(value) {
            g.color = value
        }

    fun paintString(text: Any, location: CoordinatePoint) {
        val locationOnScreen = system.toScreenPoint(location)
        g.drawString(text.toString(), locationOnScreen.x.toInt(), locationOnScreen.y.toInt())
    }


    fun paintCoordinatePoints(vararg ps: CoordinatePoint) = paintCoordinatePoints(ps.toList())

    //  todo 应对界面外的绘画做取消
    fun paintCoordinatePoints(ps: List<CoordinatePoint>) {
        val p = Polygon()

        system.toScreenPoint(ps).forEach {
            if (it.available) {
                p.addPoint(it)
            } else {
                g.drawPolyline(p)
                p.reset()
            }
        }

        g.drawPolyline(p)
    }

    //Graphics提供了fillPolygon(Polygon)，却没有drawPolyline(Polygon)，特此扩展
    fun Graphics.drawPolyline(p: Polygon) {
        if (p.npoints > 0) drawPolyline(p.xpoints, p.ypoints, p.npoints)
    }

    fun Polygon.addPoint(it: PointDouble) = addPoint(it.x.toInt(), it.y.toInt())

    /**
     * 画出一个点的位置
     * 向各个方向做垂线，各个垂点继续做垂线
     * 比如三维就是一个长方体，二维是一个长方形
     */
    fun paintLocation(p: CoordinatePoint) {
        val pa = p.toPointForAxes()
        for (i in 0..pa.size - 1) {
            if (pa.getValue(i) != 0.0) {
                val p0 = pa.setAtAndNew(i, 0.0)
                paintLine(pa, p0)

                paintLocation(p0)
            }
        }
    }

    fun Graphics.drawLine(x1: Number, y1: Number, x2: Number, y2: Number)
            = drawLine(x1.toInt(), y1.toInt(), x2.toInt(), y2.toInt())

    //直线
    fun paintStraightLine(origin: CoordinatePoint, towards: CoordinatePoint)
            = paintStraightLine(system.toScreenPoint(origin), system.toScreenPoint(towards))

    fun paintStraightLine(origin: PointDouble, towards: PointDouble) {
        if (origin.x == towards.x) {
            g.drawLine(origin.x, 0, towards.x, height)
        } else {
            val line = Line(origin, towards)

            val left = Line.Y_AXIS
            val right = Line(PointDouble(width, 0.0), PointDouble(width, height))

            val start = line.getCross(left)
            val end = line.getCross(right)

            paintLine(start, end)
        }
    }

    fun paintRayLine(origin: CoordinatePoint, towards: CoordinatePoint)
            = paintRayLine(system.toScreenPoint(origin), system.toScreenPoint(towards))

    fun paintRayLine(origin: PointDouble, towards: PointDouble) {
        if (origin.x == towards.x) {
            if (origin.y < towards.y) {
                g.drawLine(origin.x, origin.y, towards.x, height)
            } else {
                g.drawLine(origin.x, origin.y, towards.x, 0)
            }
        } else {
            val line = Line(origin, towards)
            if (origin.x > towards.x) {
                paintLine(origin, line.getCross(Line.Y_AXIS))
            } else {
                paintLine(origin, line.getCross(Line(PointDouble(width, 0), PointDouble(width, height))))
            }
        }
    }

    //线段
    fun paintLine(from: CoordinatePoint, to: CoordinatePoint) = paintLine(system.toScreenPoint(from), system.toScreenPoint(to))

    fun paintLine(from: PointDouble, to: PointDouble) = g.drawLine(from.x.toInt(), from.y.toInt(), to.x.toInt(), to.y.toInt())
}