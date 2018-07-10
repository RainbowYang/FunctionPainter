package rainbow.coordinates.two

import rainbow.coordinates.CoordinateSystem
import rainbow.point.CoordinatePoint
import rainbow.point.Point2D
import rainbow.utils.asPoint2D
import rainbow.utils.screenHeight
import rainbow.utils.screenWidth
import java.awt.event.KeyEvent.*
import java.awt.image.BufferedImage
import java.lang.Math.toDegrees
import java.lang.Math.toRadians

/**
 * 默认实现平移，旋转，伸缩
 * @author Rainbow Yang
 */
abstract class CoordinateSystem2D : CoordinateSystem() {

    var width = screenWidth
    var height = screenHeight

    var xRate: Double = 0.5
    var yRate: Double = 0.5

    var x
        get() = width * xRate
        set(x) {
            xRate = x / width
        }

    var y
        get() = height * yRate
        set(y) {
            yRate = y / height
        }

    open var zoomRate = 40.0

    open var rotatedAngle = 0.0

    var rotatedAngleAsDegree: Double
        get() = toDegrees(rotatedAngle)
        set(value) {
            rotatedAngle = toRadians(value)
        }


    var moveSpeed = 600
    var zoomSpeed = 1.3
    var rotateSpeed = 1


    fun move(x: Number, y: Number) {
        this.x += x.toDouble()
        this.y += y.toDouble()
    }

    fun moveTo(x: Number, y: Number) {
        this.x = x.toDouble()
        this.y = y.toDouble()
    }

    fun moveTo(to: Point2D) = moveTo(to.x, to.y)
    fun moveTo(to: CoordinatePoint) = moveTo(toScreenPoint(to))

    fun rotate(angle: Number) {
        rotatedAngle += angle.toDouble()
    }

    fun zoom(times: Number) {
        zoomRate *= times.toDouble()
    }

    override fun toScreenPoint(cp: CoordinatePoint) = cp.asPoint2D.rotateAndScaleAndMove()

    override fun toCoordinatePoint(pd: Point2D) = pd.inverseRotateAndScaleAndMove().asAxes

    fun Point2D.rotateAndScaleAndMove(): Point2D = with(this@CoordinateSystem2D) {
        val result = (spin(rotatedAngle) * zoomRate).asPoint2D

        return Point2D(this.x + result.x, this.y - result.y)
    }

    fun Point2D.inverseRotateAndScaleAndMove(): Point2D {
        val system = this@CoordinateSystem2D
        val result = Point2D(this.x - system.x, system.y - this.y).spin(-rotatedAngle) / zoomRate
        return result.asPoint2D
    }

    override var painter: CoordinateSystem.Painter<out CoordinateSystem> = Painter(this)

    open class Painter(cs: CoordinateSystem2D) : CoordinateSystem.Painter<CoordinateSystem2D>(cs) {
        override fun paintImage(width: Int, height: Int): BufferedImage {
            cs.width = width
            cs.height = height
            return super.paintImage(width, height)
        }
    }


    override var keyHandles: CoordinateSystem.KeyHandles<out CoordinateSystem> = KeyHandles(this)

    open class KeyHandles(cs: CoordinateSystem2D) : CoordinateSystem.KeyHandles<CoordinateSystem2D>(cs) {
        init {
            cs.apply {
                VK_W { move(0, -moveSpeed * it) }
                VK_S { move(0, moveSpeed * it) }
                VK_A { move(-moveSpeed * it, 0) }
                VK_D { move(moveSpeed * it, 0) }

                VK_Q { rotate(rotateSpeed * it) }
                VK_E { rotate(-rotateSpeed * it) }

                VK_R { zoom(Math.pow(zoomSpeed, it)) }
                VK_F { zoom(Math.pow(zoomSpeed, -it)) }
            }
        }
    }
}
