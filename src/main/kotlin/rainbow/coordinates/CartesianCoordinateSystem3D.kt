package rainbow.coordinates

import rainbow.point.*
import rainbow.utils.CoordinateGraphics
import rainbow.utils.asPoint2D
import rainbow.utils.asPoint3D
import rainbow.utils.asPoint3DSpherical
import rainbow.utils.math.power
import java.awt.event.KeyEvent.*
import java.awt.image.BufferedImage
import java.lang.Math.PI

/**
 * @author Rainbow Yang
 */
class CartesianCoordinateSystem3D : CoordinateSystem() {

    var lookingFrom: CoordinatePoint = Point3D(0, 0, 10)
    var towards: CoordinatePoint = Point3D(10, 10, -10)

    var zoomTimes = 40.0
    var centerOfSight = Point2D()

    override var painter: CoordinateSystem.Painter<out CoordinateSystem> = Painter(this)
    override var keyHandles: CoordinateSystem.KeyHandles<out CoordinateSystem> = KeyHandles(this)

    override fun toScreenPoint(cp: CoordinatePoint): Point2D {
        val (r, theta, phi) = towards.asPoint3DSpherical
        val location = (cp - lookingFrom)
        val (x, y, z) = location.asPoint3D.spinAtYX(phi).asPoint3D.spinAtXZ(theta)

        return ((Point2D(-y, x) * (zoomTimes * r / z)) + centerOfSight).asPoint2D
    }

    class Painter(cs: CartesianCoordinateSystem3D) : CoordinateSystem.Painter<CartesianCoordinateSystem3D>(cs) {

        override fun paintImage(width: Int, height: Int): BufferedImage {
            cs.centerOfSight = Point2D(width / 2, height / 2)
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

    class KeyHandles(cs: CartesianCoordinateSystem3D) : CoordinateSystem.KeyHandles<CartesianCoordinateSystem3D>(cs) {
        init {
            cs.apply {
                VK_W { lookingFrom += towards.asPoint2D * it }
                VK_S { lookingFrom -= towards.asPoint2D * it }
                VK_A { lookingFrom += towards.asPoint2D.spin(PI / 2) * it }
                VK_D { lookingFrom -= towards.asPoint2D.spin(PI / 2) * it }
                VK_2 { lookingFrom += Point3D(0, 0, towards.length) * it }
                VK_X { lookingFrom -= Point3D(0, 0, towards.length) * it }

                VK_Q { towards = towards.asPoint3D.spinAtXY(PI / 2 * it) }
                VK_E { towards = towards.asPoint3D.spinAtXY(PI / 2 * -it) }

                VK_R { towards *= 1.3 power it }
                VK_F { towards /= 1.3 power it }
            }
        }
    }
}
