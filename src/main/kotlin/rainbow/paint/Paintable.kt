package rainbow.paint

import rainbow.coordinate.point.PointDouble
import java.awt.Graphics
import java.awt.geom.Dimension2D

/**
 * 实现此类后，应该创建Painter的子类并对painter进行初始化
 * @author Rainbow Yang
 */
interface Paintable {

    var painter: Painter

    fun paint(g: Graphics, width: Number, height: Number) = painter.paint(g, width, height)

    //简易操作
    fun paint(g: Graphics, dimension2D: Dimension2D) = paint(g, dimension2D.width, dimension2D.height)

    fun paint(g: Graphics, pointDouble: PointDouble) = paint(g, pointDouble.x, pointDouble.y)

}

interface Painter {
    fun paint(g: Graphics, width: Number, height: Number) = paint(g, width.toDouble(), height.toDouble())
    fun paint(g: Graphics, width: Double, height: Double)
}