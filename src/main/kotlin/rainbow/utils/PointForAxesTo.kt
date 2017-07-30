package rainbow.utils

import rainbow.point.*

/**
 * 这里放置所有的点转换为其他点的方法，但其逻辑应写在其对应的类中
 *
 * 目前的类有[PointDouble],[PointForAxes],[PointPolar2D]
 */

fun PointForAxes.toPointDouble() = PointDouble(this)
fun PointForAxes.toPointPolar2D() = PointPolar2D(this)

fun CoordinatePoint.toPointDouble() = toPointForAxes().toPointDouble()
fun CoordinatePoint.toPointPolar2D() = toPointForAxes().toPointPolar2D()

fun PointDouble.toPointPolar2D() = toPointForAxes().toPointPolar2D()

fun PointPolar2D.toPointDouble() = toPointForAxes().toPointDouble()