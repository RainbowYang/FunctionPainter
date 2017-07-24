package rainbow.coordinate.system

import rainbow.ables.Paintable
import rainbow.coordinate.point.CoordinatePoint
import rainbow.coordinate.point.PointDouble

/**
 * 坐标系接口
 * @author Rainbow Yang
 */
interface CoordinateSystem : Paintable {
    fun toScreenPoint(cp: CoordinatePoint): PointDouble

    fun toScreenPoint(points: List<CoordinatePoint>) = List(points.size) { toScreenPoint(points[it]) }

    fun toCoordinatePoint(pd: PointDouble): CoordinatePoint

    fun toCoordinatePoint(points: List<PointDouble>) = List(points.size) { toCoordinatePoint(points[it]) }
}
