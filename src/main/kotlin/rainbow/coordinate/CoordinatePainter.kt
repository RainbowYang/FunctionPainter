package rainbow.coordinate

import rainbow.ables.Painter
import rainbow.ables.newImage
import rainbow.coordinate.graphics.CoordinateGraphics
import rainbow.coordinate.system.CoordinateSystem

/**
 * 所有使用CoordinateGraphics的画笔可以继承此类
 * 将Graphics转换为CoordinateGraphics
 * @author Rainbow Yang
 */
abstract class CoordinatePainter(var coordinateSystem: CoordinateSystem) : Painter() {

    override fun repaint() {
        val cg = CoordinateGraphics(paintingImage, coordinateSystem)

        paintByCoordinateGraphics(cg)

        callback()
    }

    open fun paintByCoordinateGraphics(cg: CoordinateGraphics) {}

}