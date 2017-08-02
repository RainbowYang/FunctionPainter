package rainbow.component

import rainbow.point.CoordinatePoint
import rainbow.point.Point2D

/**
 * 坐标转换组件
 * 负责[CoordinatePoint]和[Point2D]之间的相互转换
 * @author Rainbow Yang
 */
abstract class CoordinateTransformComponent {

    abstract fun toScreenPoint(cp: CoordinatePoint): Point2D

    abstract fun toCoordinatePoint(pd: Point2D): CoordinatePoint

}