package rainbow.utils.math

import rainbow.coordinates.two.CoordinateSystem2D
import java.awt.event.MouseEvent
import java.lang.Math.abs
import java.lang.Math.atan2

val PI2 = 2 * Math.PI

//本方法用来解决0.9999999999 ！= 1 的尴尬情况
infix fun Number.almostEquals(other: Number) = abs(this.toDouble() - other.toDouble()) < 1E-10

fun CoordinateSystem2D.getDiffAngle(form: MouseEvent, to: MouseEvent): Double {
    val toAngle = atan2(y - to.y, to.x - x)
    val formAngle = atan2(y - form.y, form.x - x)
    return toAngle - formAngle
}

fun until(end: Int) = 0 until end

/**
 * 求各值平方和的算术平方根
 */
fun lengthOf(vararg values: Number) = lengthOf(values.asList())

fun lengthOf(values: List<Number>) = Math.sqrt(values.sumByDouble { it.toDouble() * it.toDouble() })

fun checkValues(vararg values: Number) = checkValues(values.asList())

fun checkValues(values: List<Number>) = values.all { !it.toDouble().isNaN() || !it.toDouble().isInfinite() }