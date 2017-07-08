package rainbow.coordinate.graphics

import rainbow.coordinate.point.CoordinatePoint
import rainbow.coordinate.point.PointDouble
import rainbow.coordinate.system.CoordinateSystem
import rainbow.inner.math.Line
import rainbow.inner.system.MySystem
import rainbow.inner.system.getIntHeight
import java.awt.Color
import java.awt.Graphics
import java.awt.Polygon
import java.util.*

/**
 * 数学画笔，对Graphics进行包装
 * 可以直接用MyPoint进行操作
 * @author Rainbow Yang
 */
class MathGraphics(val g: Graphics, val coordinateSystem: CoordinateSystem) {
    companion object {
        val MODE_STRAIGHT_LINE = "Straight Line"
        val MODE_LINE_SEGMENT = "Line Segment"
        val MODE_RAY_LINE = "Ray Line"
    }

    fun setColor(color: Color) {
        g.color = color
    }

    fun paintString(string: String, p: CoordinatePoint): MathGraphics {
        val pd = coordinateSystem.toScreenPoint(p)
        g.drawString(string, pd.x.toInt(), pd.y.toInt())
        return this
    }

    fun paintPoints(vararg ps: CoordinatePoint): MathGraphics = paintPoints(Arrays.asList(*ps))


    fun paintPoints(ps: List<CoordinatePoint>): MathGraphics {
        g.drawPolyline(toPolygon(ps))
        return this
    }

    fun fillPoint(vararg ps: CoordinatePoint): MathGraphics = fillPoint(Arrays.asList(*ps))

    fun fillPoint(ps: List<CoordinatePoint>): MathGraphics {
        g.fillPolygon(toPolygon(ps))
        return this
    }

    private fun toPolygon(ps: List<CoordinatePoint>): Polygon {
        val p = Polygon()
        coordinateSystem.toScreenPoint(ps).forEach {
            if (it.x != Double.NaN && it.y != Double.NaN)
                p.addPoint(it.x.toInt(), it.y.toInt())
        }
        return p
    }

    /**
     * 画出一个点的位置
     * 向各个方向做垂线，各个垂点继续做垂线
     * 比如三维就是一个长方体，二维是一个长方形

     * @param p 要做位置的点
     * *
     * @return MathGraphics
     */
    fun paintLocation(p: CoordinatePoint): MathGraphics {
        val pa = p.toPointForAxes()
        for (i in 0..pa.size - 1) {
            if (pa.getValue(i) != 0.0) {
                val p0 = pa.changeValueAsNew(i, 0.0)
                paintLine(pa, p0)

                paintLocation(p0)
            }
        }

        return this
    }

    fun paintLine(p1: PointDouble, p2: PointDouble): MathGraphics {
        g.drawLine(p1.x.toInt(), p1.y.toInt(), p2.x.toInt(), p2.y.toInt())
        return this
    }

    fun paintLine(p1: CoordinatePoint, p2: CoordinatePoint): MathGraphics {
        paintLine(coordinateSystem.toScreenPoint(p1), coordinateSystem.toScreenPoint(p2))
        return this
    }

    /**
     * 过两点画直线，线段，射线

     * @param p1   两点之一
     * *
     * @param p2   两点之二
     * *
     * @param mode 直线，线段，射线
     * *
     * @return MathGraphics
     */
    fun paintLine(p1: CoordinatePoint, p2: CoordinatePoint, mode: String): MathGraphics {
        if (p1 == p2) {
            return this
        }
        if (mode === MODE_LINE_SEGMENT) {
            paintLine(p1, p2)
            return this
        }

        val pd1 = coordinateSystem.toScreenPoint(p1)
        val pd2 = coordinateSystem.toScreenPoint(p2)

        if (pd1.x == pd2.x) {
            when (mode) {
                MODE_STRAIGHT_LINE -> g.drawLine(pd1.x.toInt(), 0, pd2.x.toInt(), MySystem.getIntHeight())
                MODE_RAY_LINE -> if (pd1.y < pd2.y) {
                    g.drawLine(pd1.x.toInt(), pd1.y.toInt(), pd2.x.toInt(), MySystem.getIntHeight())
                } else {
                    g.drawLine(pd1.x.toInt(), pd1.y.toInt(), pd2.x.toInt(), 0)
                }
            }
            return this
        }


        val line = Line(pd1, pd2)
        //屏幕左边
        val left = Line.Y_AXIS
        //屏幕右边
        val right = Line(PointDouble(MySystem.width, 0.0), PointDouble(MySystem.width, MySystem.height))

        val start = line.getCross(left)
        val end = line.getCross(right)

        when (mode) {
            MODE_RAY_LINE -> if (pd1.x > pd2.x) {
                paintLine(pd1, start)
            } else {
                paintLine(pd1, end)
            }
            MODE_STRAIGHT_LINE -> paintLine(start, end)
        }
        return this

    }
}