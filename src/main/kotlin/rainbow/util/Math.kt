package rainbow.util

import rainbow.coordinate.system.cartesian.CartesianCoordinateSystem
import java.awt.event.MouseEvent

/**
 * 一些与数学有关的方法
 * @author Rainbow Yang
 */

infix fun Number.similarEquals(other: Number) = Math.abs(this.toInt() - other.toInt()) < 1E-5

fun CartesianCoordinateSystem.getDiffAngle(form: MouseEvent, to: MouseEvent): Double {
    val toAngle = Math.atan2(y - to.y, to.x - x)
    val formAngle = Math.atan2(y - form.y, form.x - x)
    return toAngle - formAngle
}
