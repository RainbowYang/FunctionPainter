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

    override var painter: CoordinateSystem.Painter<out CoordinateSystem> = Painter(this)
    override var keyHandles: CoordinateSystem.KeyHandles<out CoordinateSystem> = KeyHandles(this)

    override fun toScreenPoint(cp: CoordinatePoint): Point2D {
        val (r, theta, phi) = towards.asPoint3DSpherical
        val location = (cp - camera)
        val (x, y, z) = location.asPoint3D.spinAtYX(phi).asPoint3D.spinAtXZ(theta)

        return ((Point2D(-y, x) * (axisLength * r / z)) + centerOfSight).asPoint2D
    }

    class Painter(cs: CartesianCoordinateSystem3D) : CoordinateSystem.Painter<CartesianCoordinateSystem3D>(cs) {

        override fun paintImage(width: Int, height: Int): BufferedImage {

            cs.centerOfSight = Point2D(width / 2.0, height / 2.0)

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

    class KeyHandles(cs: CartesianCoordinateSystem3D) : CoordinateSystem.KeyHandles<CartesianCoordinateSystem3D>(cs) {
        init {
            cs.apply {
                VK_Q {
                    rotate(1 * it * 0.001)
                }
                VK_E {
                    rotate(-1 * it * 0.001)
                }
            }
        }
    }
}
