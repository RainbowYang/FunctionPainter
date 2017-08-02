package rainbow.utils

import rainbow.coordinates.CoordinateSystem2D
import rainbow.point.CoordinatePoint
import rainbow.point.Point2D

fun CoordinateSystem2D.moveTo(to: Point2D) = moveTo(to.x, to.y)
fun CoordinateSystem2D.moveTo(to: CoordinatePoint) = moveTo(toScreenPoint(to))
