package rainbow.utils

import rainbow.point.*

/**
 * 这里放置所有的点转换为其他点的方法，但其逻辑应写在其对应的类中
 *
 * 目前的类有[PointDouble],[PointForAxes],[Point2DPolar]
 */

fun PointForAxes.toPointDouble() = PointDouble(this)
fun PointForAxes.toPoint2DPolar() = Point2DPolar(this)

fun CoordinatePoint.toPointDouble() = toPointForAxes().toPointDouble()
fun CoordinatePoint.toPoint2DPolar() = toPointForAxes().toPoint2DPolar()

fun PointDouble.toPoint2DPolar() = toPointForAxes().toPoint2DPolar()

fun Point2DPolar.toPointDouble() = toPointForAxes().toPointDouble()