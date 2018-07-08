package rainbow.coordinates

import rainbow.component.input.key.KeyHandles
import rainbow.component.input.key.KeyObservable
import rainbow.component.input.key.KeyObserver
import rainbow.component.paint.CoordinatePainter
import rainbow.component.paint.Paintable
import rainbow.point.CoordinatePoint
import rainbow.point.Point2D
import rainbow.utils.CoordinateGraphics

/**
 * 坐标系
 *
 * @author Rainbow Yang
 */
abstract class CoordinateSystem : Paintable, KeyObserver {

    companion object {
        val Empty = object : CoordinateSystem() {}
    }

    open lateinit var painter: Painter<out CoordinateSystem>
    override fun getPaintedImage(width: Int, height: Int) = painter.getPaintedImage(width, height)

    open val keyHandles: KeyHandles = KeyHandles.Empty
    override fun registerTo(observable: KeyObservable) = keyHandles.registerTo(observable)


    /**
     * 将[CoordinatePoint] (坐标系中的点)转换为[Point2D] (屏幕上的点)
     */
    open fun toScreenPoint(cp: CoordinatePoint): Point2D {
        throw UnsupportedOperationException("toCoordinatePoint is not supported")
    }

    fun toScreenPoint(points: List<CoordinatePoint>) = List(points.size) { toScreenPoint(points[it]) }

    /**
     * 将[Point2D] (屏幕上的点)转换为[CoordinatePoint] (坐标系中的点)
     */
    open fun toCoordinatePoint(pd: Point2D): CoordinatePoint {
        throw UnsupportedOperationException("toCoordinatePoint is not supported")
    }

    fun toCoordinatePoint(points: List<Point2D>) =
            List(points.size) { toCoordinatePoint(points[it]) }

    open class Painter<CS : CoordinateSystem>(cs: CS) : CoordinatePainter<CS>(cs) {

        val ORIGIN: String = "Origin"
        val GRID: String = "Grid"
        val AXES: String = "Axes"
        val NUMBER: String = "Number"

        init {
            ORIGIN("#FFFFFF") { paintOrigin(it) }
            GRID("#539EB7") { paintGrid(it) }
            AXES("#FFFFFF") { paintAxes(it) }
            NUMBER("#FFFFFF") { paintNumber(it) }
        }

        open fun paintOrigin(cg: CoordinateGraphics) {}
        open fun paintGrid(cg: CoordinateGraphics) {}
        open fun paintAxes(cg: CoordinateGraphics) {}
        open fun paintNumber(cg: CoordinateGraphics) {}

    }

}
