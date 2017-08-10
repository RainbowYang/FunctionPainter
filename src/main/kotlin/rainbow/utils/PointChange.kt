package rainbow.utils

import rainbow.point.*

/*
 * 这里放置所有的点转换为其他点的方法，但其逻辑应写在其对应的类中
 */

val PointAxes.asPoint2D get() = Point2D(this)
val PointAxes.asPoint2DPolar get() = Point2DPolar(this)
val PointAxes.asPoint2DHyperbolic get() = Point2DHyperbolic(this)
val PointAxes.asPoint2DParabolic get() = Point2DParabolic(this)
val PointAxes.asPoint3D get() = Point3D(this)

val CoordinatePoint.asPoint2D get() = asAxes.asPoint2D
val CoordinatePoint.asPoint2DPolar get() = asAxes.asPoint2DPolar
val CoordinatePoint.asPoint2DHyperbolic get() = asAxes.asPoint2DHyperbolic
val CoordinatePoint.asPoint2DParabolic get() = asAxes.asPoint2DParabolic
val CoordinatePoint.asPoint3D get() = asAxes.asPoint3D

