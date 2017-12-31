package rainbow.component.paint

import rainbow.coordinates.CoordinateSystem
import rainbow.utils.CoordinateGraphics
import java.awt.Graphics2D

/**
 * @author Rainbow Yang
 */

open class CoordinatePainter(var cs: CoordinateSystem) : Painter() {
    operator fun String.invoke(color: String = "#000000",
                               visible: Boolean = true,
                               cgPaint: (CoordinateGraphics) -> Unit = {}) {

        //将(CoordinateGraphics) -> Unit 包装为 (Graphics2D) -> Unit
        val paint: (Graphics2D) -> Unit = { cgPaint(it.toCoordinateGraphics()) }
        val paintPart = PaintPart(this, color, visible, paint)

        paintParts.add(paintPart)
    }

    private fun Graphics2D.toCoordinateGraphics() = CoordinateGraphics(this, cs, width, height)

}