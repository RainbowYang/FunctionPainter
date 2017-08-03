package rainbow.coordinates

import rainbow.component.CoordinateTransformComponent
import rainbow.component.InputListenComponent
import rainbow.component.PaintComponent
import rainbow.point.CoordinatePoint
import rainbow.point.Point2D
import java.awt.Component
import java.awt.Graphics2D
import java.awt.image.BufferedImage

/**
 * 坐标系接口
 *
 * 由[CoordinateTransformComponent]，[PaintComponent]，[InputListenComponent]组成子类应实现这三个类
 *
 * @author Rainbow Yang
 *
 * @see CoordinateTransformComponent
 * @see PaintComponent
 * @see InputListenComponent
 */
abstract class CoordinateSystem {
    abstract var type: String

    abstract var coordinateTransformComponent: CoordinateTransformComponent
    abstract var paintComponent: PaintComponentOfCoordinateSystem
    abstract var inputComponent: InputListenComponent

    fun toScreenPoint(cp: CoordinatePoint): Point2D = coordinateTransformComponent.toScreenPoint(cp)
    fun toCoordinatePoint(pd: Point2D): CoordinatePoint = coordinateTransformComponent.toCoordinatePoint(pd)

    fun toScreenPoint(points: List<CoordinatePoint>) = List(points.size) { toScreenPoint(points[it]) }
    fun toCoordinatePoint(points: List<Point2D>) = List(points.size) { toCoordinatePoint(points[it]) }

    fun paintImageTo(graphics: Graphics2D) = paintComponent.paintImageTo(graphics)
    fun paintedImage(): BufferedImage = paintComponent.paintedImage()

    fun bindTo(component: Component) = inputComponent.bindTo(component)
}
