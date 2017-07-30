package rainbow.utils

import rainbow.coordinates.CoordinateSystem2D
import java.awt.event.MouseEvent

import java.lang.Math.*

/**
 * 一些与数学有关的方法
 * @author Rainbow Yang
 */

val PI2 = 2 * Math.PI

//本方法用来解决0.9999999999 ！= 1 的尴尬情况
infix fun Number.similarEquals(other: Number) = abs(this.toDouble() - other.toDouble()) < 1E-10

fun CoordinateSystem2D.getDiffAngle(form: MouseEvent, to: MouseEvent): Double {
    val toAngle = atan2(y - to.y, to.x - x)
    val formAngle = atan2(y - form.y, form.x - x)
    return toAngle - formAngle
}

fun rangeTo(end: Int) = 0..end - 1

fun length(x: Number, y: Number): Double {
    x as Double;y as Double
    return sqrt(x * x + y * y)
}