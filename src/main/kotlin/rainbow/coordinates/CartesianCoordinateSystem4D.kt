package rainbow.coordinates

import rainbow.point.*
import rainbow.utils.*
import rainbow.utils.math.lengthOf
import java.awt.event.KeyEvent.*
import java.awt.image.BufferedImage

/**
 * @author Rainbow Yang
 */
class CartesianCoordinateSystem4D : CoordinateSystem() {


    var camera: CoordinatePoint = PointAxes(0, 0, 10)
    var towards: CoordinatePoint = PointSpherical(PointAxes(10, 0, -10, 10))

    /**
     * 单位长度
     */
    var axisLength = 40.0

    var centerOfSight = Point2D(0, 0)

    fun rotate(angle: Number) {
        towards = towards.asPoint3D.spinAtXY(angle).asPoint3DSpherical
    }

    override var painter: CoordinateSystem.Painter = Painter()

    override val keyHandles: rainbow.component.input.key.KeyHandles = KeyHandles()

    override fun toScreenPoint(cp: CoordinatePoint): Point2D {
        var location = (cp - camera).asAxes

        val (_, angles) = towards.asPointSpherical

        angles.indices.forEach {
            location = location.spinAtAndNew(0, it + 1, -angles[it])
        }

        val (_, y, z) = location
//        val xyLength = lengthOf(y, z)

        val length = location.setAtAndNew(1, 0).setAtAndNew(2, 0).length

        return (Point2D(-y, -z) * (axisLength * towards.length / length) + centerOfSight).asPoint2D

    }

    inner class Painter : CoordinateSystem.Painter() {

        override fun paintImage(width: Int, height: Int): BufferedImage {

            centerOfSight = Point2D(width / 2.0, height / 2.0)

            return super.paintImage(width, height)
        }

        override fun paintOrigin(cg: CoordinateGraphics) = cg.paintString("O")

        override fun paintGrid(cg: CoordinateGraphics) {
            (-30..30).forEach {
                cg.paintStraightLine(Point2D(it, 0), Point2D(it, 1))
                cg.paintStraightLine(Point2D(0, it), Point2D(1, it))
            }
        }

        override fun paintAxes(cg: CoordinateGraphics) {
            cg.paintRayLine(PointAxes.ZERO, Point3D(1, 0, 0))
            cg.paintRayLine(PointAxes.ZERO, Point3D(0, 1, 0))
            cg.paintRayLine(PointAxes.ZERO, Point3D(0, 0, 1))
        }

    }

    inner class KeyHandles : rainbow.component.input.key.KeyHandles() {
        init {
            VK_Q { rotate(1 * it * 0.001) }
            VK_E { rotate(-1 * it * 0.001) }

            VK_W { camera += (towards.asAxes.setAtAndNew(2, 0) * it * 0.001) }
            VK_S { camera += (towards.asAxes.setAtAndNew(2, 0) * -it * 0.001) }
            VK_A {
                camera += (towards.asAxes.setAtAndNew(2, 0)
                        .spinAtAndNew(0, 1, Math.PI / 2) * it * 0.001)
            }
            VK_D {
                camera += (towards.asAxes.setAtAndNew(2, 0)
                        .spinAtAndNew(0, 1, Math.PI / 2) * -it * 0.001)
            }

            VK_SPACE { camera += Point3D(0, 0, 3) * it * 0.001 }
            VK_Z { camera -= Point3D(0, 0, 3) * it * 0.001 }

        }
    }
}
