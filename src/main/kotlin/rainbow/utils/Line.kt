package rainbow.utils

import rainbow.point.PointDouble

/**
 * 数学意义上的线
 * 提供求交点等功能
 * 表达式为a*x+b*y+c=0

 * @author Rainbow Yang
 */
class Line(val a: Double, val b: Double, val c: Double) {
    constructor(a: Number, b: Number, c: Number) : this(a.toDouble(), b.toDouble(), c.toDouble())

    companion object {
        /**
         * 根据两个点生成线
         */
        operator fun invoke(p1: PointDouble, p2: PointDouble): Line {
            val a = p1.y - p2.y
            val b = -(p1.x - p2.x)
            val c = p1.x * p2.y - p1.y * p2.x
            return Line(a, b, c)
        }

        /**
         * 根据一个点和倾斜角生成线
         */
        operator fun invoke(point: PointDouble, angle: Double) =
                if ((angle - Math.PI / 2) % Math.PI similarEquals 0.0) {
                    Line(1, 0, -point.x)
                } else {
                    val a = Math.tan(angle)
                    Line(a, -1, point.y * (1 - a))
                }

        val X_AXIS = Line(0.0, 1.0, 0.0)
        val Y_AXIS = Line(1.0, 0.0, 0.0)
    }

    infix fun crossTo(l: Line): PointDouble {
        //平行
        if (a * l.b - b * l.a == 0.0) {
            throw NoCrossException(this.toString() + "has no cross with" + l)
        }
        return PointDouble(-(c * l.b - b * l.c) / (a * l.b - b * l.a), -(a * l.c - c * l.a) / (a * l.b - b * l.a))
    }

    override fun toString(): String {
        return "Line($a*x+$b*y+$c=0)"
    }

    internal inner class NoCrossException(message: String) : RuntimeException(message)
}
