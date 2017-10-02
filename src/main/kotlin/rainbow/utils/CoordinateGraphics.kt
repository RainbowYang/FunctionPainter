package rainbow.utils

import rainbow.coordinates.CoordinateSystem
import rainbow.point.CoordinatePoint
import rainbow.point.Point2D
import rainbow.point.PointAxes
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Polygon
import java.awt.image.BufferedImage

/**
 * 坐标画笔，对Graphics进行包装
 * 可以直接用CoordinatePoint进行操作
 *
 * @see CoordinatePoint
 * @author Rainbow Yang
 */
class CoordinateGraphics(val g: Graphics2D, val system: CoordinateSystem,
                         var width: Number = screenWidth, var height: Number = screenHeight) {

    constructor(image: BufferedImage, system: CoordinateSystem) : this(image.createGraphics(), system)

    init {
        g.antialias()
    }

    var color: Color
        get () = g.color
        set(value) {
            g.color = value
        }

    fun setSize(width: Number, height: Number): CoordinateGraphics {
        this.width = width
        this.height = height

        return this
    }

    fun paintString(text: Any, location: CoordinatePoint = PointAxes.ZERO) {
        val locationOnScreen = system.toScreenPoint(location)
        g.drawString(text.toString(), locationOnScreen.x.toInt(), locationOnScreen.y.toInt())
    }

    fun paintCoordinatePoints(vararg ps: CoordinatePoint) = paintCoordinatePoints(ps.toList())

    fun paintCoordinatePoints(ps: List<CoordinatePoint>) {
        val p = Polygon()

        system.coordinator.toScreenPoint(ps).forEach {
            if (it.available) {
                p.addPoint(it)
            } else {
                g.drawPolyline(p)
                p.reset()
            }
        }

        g.drawPolyline(p)
    }

    /**
     * 画出一个点的位置
     * 向各个方向做垂线，各个垂点继续做垂线
     * 比如三维就是一个长方体，二维是一个长方形
     */
    fun paintLocation(p: CoordinatePoint) {
        val pa = p.asAxes
        until(pa.size).forEach {
            if (pa[it] != 0.0) {
                val p0 = pa.setAtAndNew(it, 0.0)
                paintLine(pa, p0)

                paintLocation(p0)
            }
        }
    }

    fun Graphics2D.drawLine(x1: Number, y1: Number, x2: Number, y2: Number)
            = drawLine(x1.toInt(), y1.toInt(), x2.toInt(), y2.toInt())

    //直线
    fun paintStraightLine(origin: CoordinatePoint, towards: CoordinatePoint)
            = paintStraightLine(system.toScreenPoint(origin), system.toScreenPoint(towards))

    private fun paintStraightLine(origin: Point2D, towards: Point2D) {
        if (origin.x almostEquals towards.x) {
            g.drawLine(origin.x, 0, towards.x, height)
        } else {
            val line = Line(origin, towards)

            val left = Line.Y_AXIS
            val right = Line(Point2D(width, 0.0), Point2D(width, height))

            val start = line.crossTo(left)
            val end = line.crossTo(right)

            paintLine(start, end)
        }
    }

    fun paintRayLine(origin: CoordinatePoint, towards: CoordinatePoint)
            = paintRayLine(system.toScreenPoint(origin), system.toScreenPoint(towards))

    private fun paintRayLine(origin: Point2D, towards: Point2D) {
        if (origin.x almostEquals towards.x) {
            if (origin.y < towards.y) {
                g.drawLine(origin.x, origin.y, towards.x, height)
            } else {
                g.drawLine(origin.x, origin.y, towards.x, 0)
            }
        } else {
            val line = Line(origin, towards)
            if (origin.x > towards.x) {
                paintLine(origin, line.crossTo(Line.Y_AXIS))
            } else {
                paintLine(origin, line.crossTo(Line(Point2D(width, 0), Point2D(width, height))))
            }
        }
    }

    //线段
    fun paintLine(from: CoordinatePoint, to: CoordinatePoint) = paintLine(system.toScreenPoint(from), system.toScreenPoint(to))

    private fun paintLine(from: Point2D, to: Point2D) = g.drawLine(from.x.toInt(), from.y.toInt(), to.x.toInt(), to.y.toInt())

    /**
     * 以[center]为中心，[center]到[to]为半径
     */
    fun paintCircle(center: CoordinatePoint, to: CoordinatePoint) {
        val centerPd = system.toScreenPoint(center)
        val x = centerPd.x.toInt()
        val y = centerPd.y.toInt()

        val r = system.toScreenPoint(to).minus(centerPd).length.toInt()

        g.drawOval(x - r, y - r, r * 2, r * 2)
    }
}