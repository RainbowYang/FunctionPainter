package rainbow.component

import rainbow.point.CoordinatePoint
import rainbow.point.PointDouble

/**
 * 坐标转换组件
 *
 * @author Rainbow Yang
 */
abstract class CoordinateTransformComponent {

    abstract fun toScreenPoint(cp: CoordinatePoint): PointDouble

    abstract fun toCoordinatePoint(pd: PointDouble): CoordinatePoint

}