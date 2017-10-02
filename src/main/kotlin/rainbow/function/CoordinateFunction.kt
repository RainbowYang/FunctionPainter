package rainbow.function

import rainbow.component.Paintable
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

    lateinit var coordinateSystem: CoordinateSystem

    override fun paintedImage(width: Int, height: Int) = painter.paintedImage(width, height)

    open fun init() {}

    abstract inner class Painter : rainbow.component.Painter() {

        init {
            "before"{ paintBefore(it) }
            "main"("#FFFFFF"){ paintMain(it) }
            "after"{ paintAfter(it) }
        }

        private fun paintBefore(g: Graphics2D) = paintBefore(getCoordinateGraphicsAndSetSize(g))
        private fun paintMain(g: Graphics2D) = paintMain(getCoordinateGraphicsAndSetSize(g))
        private fun paintAfter(g: Graphics2D) = paintAfter(getCoordinateGraphicsAndSetSize(g))

        private fun getCoordinateGraphicsAndSetSize(g: Graphics2D) = g.with(coordinateSystem).setSize(width, height)

        open fun paintBefore(cg: CoordinateGraphics) {}
        open fun paintMain(cg: CoordinateGraphics) {}
        open fun paintAfter(cg: CoordinateGraphics) {}

    }

}