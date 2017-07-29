package rainbow.utils

import rainbow.coordinates.CoordinateSystem2D
import java.awt.event.MouseEvent

import java.lang.Math.*

/**
 * 一些与数学有关的方法
 * @author Rainbow Yang
 */

infix fun Number.similarEquals(other: Number) = abs(this.toDouble() - other.toDouble()) < 1E-10

fun CoordinateSystem2D.getDiffAngle(form: MouseEvent, to: MouseEvent): Double {
    val toAngle = atan2(y - to.y, to.x - x)
    val formAngle = atan2(y - form.y, form.x - x)
    return toAngle - formAngle
}

fun rangeTo(end: Int) = 0..end - 1