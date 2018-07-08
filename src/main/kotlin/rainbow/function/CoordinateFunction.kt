package rainbow.function

import rainbow.component.paint.CoordinatePainter
import rainbow.component.paint.Paintable
import rainbow.coordinates.CoordinateSystem
import rainbow.utils.CoordinateGraphics
import rainbow.utils.with
import java.awt.Graphics2D

/**
 * 所有能画在坐标系上的东西
 * @author Rainbow Yang
 */
abstract class CoordinateFunction : Paintable {
    abstract var painter: Painter

    var coordinateSystem: CoordinateSystem
        get() = painter.cs
        set(value) {
            painter.cs = value
        }

    override fun getPaintedImage(width: Int, height: Int) = painter.getPaintedImage(width, height)

    open fun init() {}

    open class Painter : CoordinatePainter<CoordinateSystem>(CoordinateSystem.Empty) {

        init {
            "before"{ paintBefore(it) }
            "main"("#FFFFFF") { paintMain(it) }
            "after"{ paintAfter(it) }
        }

        open fun paintBefore(cg: CoordinateGraphics) {}
        open fun paintMain(cg: CoordinateGraphics) {}
        open fun paintAfter(cg: CoordinateGraphics) {}
    }
}