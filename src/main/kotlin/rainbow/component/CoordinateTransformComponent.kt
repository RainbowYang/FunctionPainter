package rainbow.component

import rainbow.point.CoordinatePoint
import rainbow.point.Point2D

/**
 * 坐标转换组件
 * 负责[CoordinatePoint]和[Point2D]之间的相互转换
 * @author Rainbow Yang
 */
abstract class CoordinateTransformComponent {

    /**
     * 将[CoordinatePoint] (坐标系中的点)转换为[Point2D] (屏幕上的点)
     */
    abstract fun toScreenPoint(cp: CoordinatePoint): Point2D

    /**
     * 将[Point2D] (屏幕上的点)转换为[CoordinatePoint] (坐标系中的点)
     */
    abstract fun toCoordinatePoint(pd: Point2D): CoordinatePoint

}