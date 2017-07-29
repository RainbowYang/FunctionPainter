package rainbow.coordinates

import rainbow.component.CoordinateTransformComponent
import rainbow.component.InputListenComponent
import rainbow.component.PaintComponent
import rainbow.point.CoordinatePoint
import rainbow.point.PointDouble
import rainbow.utils.BufferedImage
import rainbow.utils.CoordinateGraphics
import java.awt.Color
import java.awt.Component
import java.awt.Graphics2D
import java.awt.image.BufferedImage

/**
 * 坐标系接口
 *
 * 由[CoordinateTransformComponent]，[PaintComponent]，[InputListenComponent]组成
 * 子类应实现这三个类
 *
 * @author Rainbow Yang
 *
 * @see CoordinateTransformComponent
 * @see PaintComponent
 * @see InputListenComponent
 */
abstract class CoordinateSystem {

    abstract var coordinateTransformComponent: CoordinateTransformComponent
    abstract var paintComponent: CoordinateSystemPainter
    abstract var inputComponent: InputListenComponent


    fun toScreenPoint(cp: CoordinatePoint): PointDouble = coordinateTransformComponent.toScreenPoint(cp)
    fun toCoordinatePoint(pd: PointDouble): CoordinatePoint = coordinateTransformComponent.toCoordinatePoint(pd)

    fun toScreenPoint(points: List<CoordinatePoint>) = List(points.size) { toScreenPoint(points[it]) }
    fun toCoordinatePoint(points: List<PointDouble>) = List(points.size) { toCoordinatePoint(points[it]) }

    fun paintImageTo(graphics: Graphics2D) = paintComponent.paintImageTo(graphics)
    fun paintedImage(): BufferedImage = paintComponent.paintedImage()

    fun bindTo(component: Component) = inputComponent.bindTo(component)


    /**
     * CoordinateSystem的绘画组件
     *
     * @author Rainbow Yang
     */
    abstract class CoordinateSystemPainter(val coordinateSystem: CoordinateSystem) : PaintComponent() {
        var colorOfOrigin: Color = Color.BLACK
        var colorOfGrid: Color = Color.BLACK
        var colorOfAxes: Color = Color.BLACK
        var colorOfNumber: Color = Color.BLACK


        var isVisual = true

        var paintOrigin = true
        var paintGrid = true
        var paintAxes = true
        var paintNumber = true

        open fun paintOrigin(cg: CoordinateGraphics) {}
        open fun paintGrid(cg: CoordinateGraphics) {}
        open fun paintAxes(cg: CoordinateGraphics) {}
        open fun paintNumber(cg: CoordinateGraphics) {}

        override fun paintedImage(width: Int, height: Int): BufferedImage = BufferedImage(width, height).also {
            val cg = CoordinateGraphics(it, coordinateSystem)

            if (isVisual) {

                if (paintOrigin) {
                    cg.color = colorOfOrigin
                    paintOrigin(cg)
                }

                if (paintGrid) {
                    cg.color = colorOfGrid
                    paintGrid(cg)
                }

                if (paintAxes) {
                    cg.color = colorOfAxes
                    paintAxes(cg)
                }

                if (paintNumber) {
                    cg.color = colorOfNumber
                    paintNumber(cg)
                }
            }
        }
    }
}
