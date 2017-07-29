package rainbow.utils

import rainbow.coordinates.CoordinateSystem2D
import rainbow.point.CoordinatePoint
import rainbow.point.PointDouble

fun CoordinateSystem2D.moveTo(to: PointDouble) = moveTo(to.x, to.y)
fun CoordinateSystem2D.moveTo(to: CoordinatePoint) = moveTo(toScreenPoint(to))
