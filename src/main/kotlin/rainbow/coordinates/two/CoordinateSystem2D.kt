package rainbow.coordinates.two

import rainbow.coordinates.AbstractCoordinateSystem
import rainbow.point.CoordinatePoint
import rainbow.point.Point2D
import rainbow.utils.asPoint2D

/**
 * 二维的坐标系可以使用此类默认实现[toScreenPoint]和[toCoordinatePoint]
 *
 * @author Rainbow Yang
 */
abstract class CoordinateSystem2D : AbstractCoordinateSystem() {
    /**
     * 单位长度
     */
    var axisLength = 40.0

    override fun toScreenPoint(cp: CoordinatePoint) = (cp.asPoint2D * axisLength).asPoint2D.rotateAndScaleAndMove()

    override fun toCoordinatePoint(pd: Point2D) = (pd.inverseRotateAndScaleAndMove() / axisLength).asAxes
}