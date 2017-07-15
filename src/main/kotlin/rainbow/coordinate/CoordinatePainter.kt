package rainbow.coordinate

import rainbow.coordinate.graphics.CoordinateGraphics
import rainbow.coordinate.system.CoordinateSystem
import rainbow.paint.Painter
import java.awt.Graphics

/**
 * 所有使用CoordinateGraphics的画笔可以继承此类
 * 将Graphics转换为CoordinateGraphics
 * @author Rainbow Yang
 */
abstract class CoordinatePainter(val coordinateSystem: CoordinateSystem) : Painter {

    override fun paint(g: Graphics, width: Double, height: Double) =
            paintByCoordinateGraphics(CoordinateGraphics(g, coordinateSystem, width, height))

    open fun paintByCoordinateGraphics(cg: CoordinateGraphics) {}

}