package rainbow.coordinates.two

import rainbow.coordinates.CoordinateSystem
import rainbow.point.CoordinatePoint
import rainbow.point.Point2D
import rainbow.utils.asPoint2D
import java.awt.event.KeyEvent.*
import java.lang.Math.toDegrees
import java.lang.Math.toRadians

/**
 * 实现平移，旋转，伸缩
 * @author Rainbow Yang
 */
abstract class AbstractCoordinateSystem(
        x: Number = 0,
        y: Number = 0,
        zoomRate: Number = 1.0,
        rotatedAngle: Number = 0.0
) : CoordinateSystem() {

    override var keyHandles: rainbow.component.input.key.KeyHandles = KeyHandles()

    open var origin = Point2D(x, y)

    var x
        get() = origin.x
        set(x) {
            origin = Point2D(x, y)
        }
    var y
        get() = origin.y
        set(y) {
            origin = Point2D(x, y)
        }

    open var zoomRate = zoomRate.toDouble()
    open var rotatedAngle = rotatedAngle.toDouble()
    var rotatedAngleAsDegree: Double
        get() = toDegrees(rotatedAngle)
        set(value) {
            rotatedAngle = toRadians(value)
        }


    var moveSpeed = 100
    var zoomSpeed = 1.1
    var rotateSpeed = 0.1


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

    fun Point2D.rotateAndScaleAndMove(): Point2D = with(this@AbstractCoordinateSystem) {
        val result = (spin(rotatedAngle) * zoomRate).asPoint2D

        return Point2D(this.x + result.x, this.y - result.y)
    }

    fun Point2D.inverseRotateAndScaleAndMove(): Point2D {
        val system = this@AbstractCoordinateSystem
        val result = Point2D(this.x - system.x, system.y - this.y).spin(-rotatedAngle) / zoomRate
        return result.asPoint2D
    }

    inner class KeyHandles : rainbow.component.input.key.KeyHandles() {
        init {
            VK_W { move(0, -moveSpeed * it) }
            VK_S { move(0, moveSpeed * it) }
            VK_A { move(-moveSpeed * it, 0) }
            VK_D { move(moveSpeed * it, 0) }

            VK_Q { rotate(rotateSpeed * it) }
            VK_E { rotate(-rotateSpeed * it) }

            VK_R { zoom(Math.pow(zoomSpeed, it.toDouble())) }
            VK_F { zoom(Math.pow(zoomSpeed, -it.toDouble())) }
        }
    }
}
