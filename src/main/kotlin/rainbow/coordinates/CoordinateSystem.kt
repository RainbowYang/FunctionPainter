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

    /**
     * 坐标转换组件
     * @see Coordinator
     */
    abstract val coordinator: Coordinator

    /**
     * 绘画组件
     * @see Painter
     */
    open var painter: Painter = Painter()

    fun toScreenPoint(cp: CoordinatePoint) = coordinator.toScreenPoint(cp)
    fun toCoordinatePoint(pd: Point2D) = coordinator.toCoordinatePoint(pd)
    fun toScreenPoint(points: List<CoordinatePoint>) = List(points.size) { toScreenPoint(points[it]) }
    fun toCoordinatePoint(points: List<Point2D>) = List(points.size) { toCoordinatePoint(points[it]) }

    override fun paintedImage(width: Int, height: Int) = painter.paintedImage(width, height)

    override fun setKey(keyMap: KeyMap) {}

    /**
     * 坐标转换组件
     * 负责[CoordinatePoint]和[Point2D]之间的相互转换
     * @author Rainbow Yang
     */
    abstract inner class Coordinator {

        /**
         * 将[CoordinatePoint] (坐标系中的点)转换为[Point2D] (屏幕上的点)
         */
        abstract fun toScreenPoint(cp: CoordinatePoint): Point2D

        /**
         * 将[CoordinatePoint] (坐标系中的点)转换为[Point2D] (屏幕上的点)
         */
        fun toScreenPoint(points: List<CoordinatePoint>) =
                List(points.size) { toScreenPoint(points[it]) }

        /**
         * 将[Point2D] (屏幕上的点)转换为[CoordinatePoint] (坐标系中的点)
         */
        open fun toCoordinatePoint(pd: Point2D): CoordinatePoint {
            throw UnsupportedOperationException("toCoordinatePoint is not supported")
        }

        /**
         * 将[Point2D] (屏幕上的点)转换为[CoordinatePoint] (坐标系中的点)
         */
        fun toCoordinatePoint(points: List<Point2D>) =
                List(points.size) { toCoordinatePoint(points[it]) }
    }

    open inner class Painter : rainbow.component.Painter() {

        val ORIGIN: String = "Origin"
        val GRID: String = "Grid"
        val AXES: String = "Axes"
        val NUMBER: String = "Number"

        init {
            ORIGIN("#FFFFFF") { paintOrigin(getCoordinateGraphicsAndSetSize(it)) }
            GRID("#539EB7") { paintGrid(getCoordinateGraphicsAndSetSize(it)) }
            AXES("#FFFFFF") { paintAxes(getCoordinateGraphicsAndSetSize(it)) }
            NUMBER("#FFFFFF") { paintNumber(getCoordinateGraphicsAndSetSize(it)) }
        }

        private fun getCoordinateGraphicsAndSetSize(g: Graphics2D) =
                g.with(this@CoordinateSystem).setSize(width, height)

        open fun paintOrigin(cg: CoordinateGraphics) {}
        open fun paintGrid(cg: CoordinateGraphics) {}
        open fun paintAxes(cg: CoordinateGraphics) {}
        open fun paintNumber(cg: CoordinateGraphics) {}

    }

}
