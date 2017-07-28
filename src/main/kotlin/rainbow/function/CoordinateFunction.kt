package rainbow.function

import rainbow.component.PaintComponent
import rainbow.coordinates.CoordinateSystem
import rainbow.utils.BufferedImage
import rainbow.utils.CoordinateGraphics
import java.awt.Color.RED
import java.awt.Graphics2D
import java.awt.image.BufferedImage

/**
 * 所有能画在坐标系上的东西
 * @author Rainbow Yang
 */
abstract class CoordinateFunction {
    abstract var paintComponent: CoordinateFunctionPainter

    var coordinateSystem: CoordinateSystem
        get() = paintComponent.coordinateSystem
        set(value) {
            paintComponent.coordinateSystem = value
        }

    fun paintImageTo(graphics: Graphics2D) = paintComponent.paintImageTo(graphics)
    fun paintedImage(): BufferedImage = paintComponent.paintedImage()

    open fun init() {}

    abstract class CoordinateFunctionPainter : PaintComponent() {

        lateinit var coordinateSystem: CoordinateSystem

        var colorOfBefore = RED
        var colorOfMain = RED
        var colorOfAfter = RED

        open fun paintBefore(cg: CoordinateGraphics) {}
        open fun paintMain(cg: CoordinateGraphics) {}
        open fun paintAfter(cg: CoordinateGraphics) {}

        override fun paintedImage(width: Int, height: Int): BufferedImage = BufferedImage(width, height).also {
            val cg = CoordinateGraphics(it, coordinateSystem)

            cg.color = colorOfBefore
            paintBefore(cg)

            cg.color = colorOfMain
            paintMain(cg)

            cg.color = colorOfAfter
            paintAfter(cg)
        }

    }

}