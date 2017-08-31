package rainbow.coordinates

import rainbow.component.Paintable
import rainbow.component.Painter
import rainbow.input.KeyControllable
import rainbow.input.KeyMap
import rainbow.point.CoordinatePoint
import rainbow.point.Point2D
import rainbow.utils.CoordinateGraphics
import rainbow.utils.with
import java.awt.Graphics2D

/**
 * 坐标系
 *
 * @author Rainbow Yang
 */
abstract class CoordinateSystem : Paintable, KeyControllable {

    val ORIGIN: String = "Origin"
    val GRID: String = "Grid"
    val AXES: String = "Axes"
    val NUMBER: String = "Number"

    /**
     * 坐标转换组件
     * @see CoordinateTransformComponent
     */
    abstract val coordinateTransformComponent: CoordinateTransformComponent

    /**
     * 绘画组件
     * @see Painter
     */
    abstract var painter: Painter

    fun toScreenPoint(cp: CoordinatePoint) = coordinateTransformComponent.toScreenPoint(cp)

    fun toCoordinatePoint(pd: Point2D) = coordinateTransformComponent.toCoordinatePoint(pd)
    fun toScreenPoint(points: List<CoordinatePoint>) = List(points.size) { toScreenPoint(points[it]) }

    fun toCoordinatePoint(points: List<Point2D>) = List(points.size) { toCoordinatePoint(points[it]) }

    override fun paintedImage(width: Int, height: Int) = painter.paintedImage(width, height)

    /**
     * 坐标转换组件
     * 负责[CoordinatePoint]和[Point2D]之间的相互转换
     * @author Rainbow Yang
     */
    abstract inner class CoordinateTransformComponent {

        /**
         * 将[CoordinatePoint] (坐标系中的点)转换为[Point2D] (屏幕上的点)
         */
        abstract fun toScreenPoint(cp: CoordinatePoint): Point2D

        /**
         * 将[Point2D] (屏幕上的点)转换为[CoordinatePoint] (坐标系中的点)
         */
        abstract fun toCoordinatePoint(pd: Point2D): CoordinatePoint

    }

    abstract inner class Painter : rainbow.component.Painter() {

        init {
            ORIGIN { paintOrigin(it) }
            GRID { paintGrid(it) }
            AXES { paintAxes(it) }
            NUMBER { paintNumber(it) }
        }

        private fun paintOrigin(g: Graphics2D) = paintOrigin(getCoordinateGraphicsAndSetSize(g))
        private fun paintGrid(g: Graphics2D) = paintGrid(getCoordinateGraphicsAndSetSize(g))
        private fun paintAxes(g: Graphics2D) = paintAxes(getCoordinateGraphicsAndSetSize(g))
        private fun paintNumber(g: Graphics2D) = paintNumber(getCoordinateGraphicsAndSetSize(g))

        private fun getCoordinateGraphicsAndSetSize(g: Graphics2D) =
                g.with(this@CoordinateSystem).setSize(width, height)

        abstract fun paintOrigin(cg: CoordinateGraphics)
        abstract fun paintGrid(cg: CoordinateGraphics)
        abstract fun paintAxes(cg: CoordinateGraphics)
        abstract fun paintNumber(cg: CoordinateGraphics)

    }

}
