package rainbow.coordinates

import rainbow.point.*
import rainbow.utils.CoordinateGraphics
import rainbow.utils.asPoint2D
import rainbow.utils.asPoint3D
import rainbow.utils.asPoint3DSpherical
import java.awt.event.KeyEvent.VK_E
import java.awt.event.KeyEvent.VK_Q
import java.awt.image.BufferedImage

/**
 * @author Rainbow Yang
 */
class CartesianCoordinateSystem3D : CoordinateSystem() {

    var camera = Point3D(0, 0, 10)
    var towards = Point3DSpherical(Point3D(10, 0, -10))

    /**
     * 单位长度
     */
    var axisLength = 40.0

    var centerOfSight = Point2D(0, 0)

    override var painter: CoordinateSystem.Painter = Painter()
    override val keyHandles: rainbow.component.input.key.KeyHandles = KeyHandles()

    override fun toScreenPoint(cp: CoordinatePoint): Point2D {
        val (r, theta, phi) = towards.asPoint3DSpherical
        val location = (cp - camera)
        val (x, y, z) = location.asPoint3D.spinAtYX(phi).asPoint3D.spinAtXZ(theta)

        return ((Point2D(-y, x) * (axisLength * r / z)) + centerOfSight).asPoint2D
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

        override fun paintNumber(cg: CoordinateGraphics) {
            super.paintNumber(cg)
        }

    }

    fun rotate(angle: Number) {
        towards = towards.asPoint3D.spinAtXY(angle).asPoint3DSpherical
    }

    inner class KeyHandles : rainbow.component.input.key.KeyHandles() {
        init {
            VK_Q {
                rotate(1 * it * 0.001)
            }
            VK_E {
                rotate(-1 * it * 0.001)
            }
        }
    }
}
