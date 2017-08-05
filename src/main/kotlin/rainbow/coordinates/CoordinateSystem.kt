package rainbow.coordinates

import com.google.gson.annotations.Expose
import rainbow.component.CoordinateTransformComponent
import rainbow.component.InputListenComponent
import rainbow.coordinates.CoordinateSystem.PaintComponent
import rainbow.point.CoordinatePoint
import rainbow.point.Point2D
import rainbow.utils.BufferedImage
import rainbow.utils.CoordinateGraphics
import rainbow.utils.parseColor
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
    open var type = this::class.simpleName.toString()

    abstract var coordinateTransformComponent: CoordinateTransformComponent
    abstract var paintComponent: PaintComponent
    abstract var inputComponent: InputListenComponent

    fun toScreenPoint(cp: CoordinatePoint): Point2D = coordinateTransformComponent.toScreenPoint(cp)
    fun toCoordinatePoint(pd: Point2D): CoordinatePoint = coordinateTransformComponent.toCoordinatePoint(pd)

    fun toScreenPoint(points: List<CoordinatePoint>) = List(points.size) { toScreenPoint(points[it]) }
    fun toCoordinatePoint(points: List<Point2D>) = List(points.size) { toCoordinatePoint(points[it]) }

    fun paintImageTo(graphics: Graphics2D) = paintComponent.paintImageTo(graphics)
    fun paintedImage(): BufferedImage = paintComponent.paintedImage()

    fun bindTo(component: Component) = inputComponent.bindTo(component)

    /**
     * CoordinateSystem的绘画组件
     *
     * @author Rainbow Yang
     */
    abstract class PaintComponent(val coordinateSystem: CoordinateSystem) : rainbow.component.PaintComponent() {

        @Expose var visible = true

        @Expose val paints = mutableListOf<PaintPart>()

        val ORIGIN: String = "Origin"
        val GRID: String = "Grid"
        val AXES: String = "Axes"
        val NUMBER: String = "Number"

        fun addPaintPart(name: String,
                         color: String = "#000000",
                         needPaint: Boolean = true,
                         paint: (CoordinateGraphics) -> Unit = {}) {
            paints.add(PaintPart(name, color, needPaint, paint))
        }

        override fun paintedImage(width: Int, height: Int): BufferedImage = BufferedImage(width, height).also {
            if (visible) {
                val cg = CoordinateGraphics(it, coordinateSystem)

                paints.filter { it.visible }.forEach {
                    cg.color = parseColor(it.color)
                    it.paint(cg)
                }
            }
        }

        class PaintPart(@Expose var name: String,
                        @Expose var color: String,
                        @Expose var visible: Boolean,
                        var paint: (CoordinateGraphics) -> Unit)

    }

}
