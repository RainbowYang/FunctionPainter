package rainbow.coordinates

import rainbow.input.KeyMap
import rainbow.point.CoordinatePoint
import rainbow.point.Point2D
import rainbow.utils.asPoint2D
import java.awt.event.KeyEvent.*
import java.lang.Math.toDegrees
import java.lang.Math.toRadians

/**
 * 二维坐标系的接口
 * 实现平移，旋转，伸缩
 * @author Rainbow Yang
 */
abstract class CoordinateSystem2D(x: Number = 0, y: Number = 0, zoomRate: Number = 1.0, rotatedAngle: Number = 0.0) : CoordinateSystem() {

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

    fun Point2D.rotateAndScaleAndMove(): Point2D = with(this@CoordinateSystem2D) {
        val result = (spin(rotatedAngle) * zoomRate).asPoint2D

        return Point2D(this.x + result.x, this.y - result.y)
    }

    fun Point2D.inverseRotateAndScaleAndMove(): Point2D {
        val system = this@CoordinateSystem2D
        val result = Point2D(this.x - system.x, system.y - this.y).spin(-rotatedAngle) / zoomRate
        return result.asPoint2D
    }

    override fun addKeyHandlesTo(keyMap: KeyMap) {
        with(keyMap) {
            VK_UP { move(0, -1) }
            VK_DOWN { move(0, 1) }
            VK_RIGHT { move(1, 0) }
            VK_LEFT { move(-1, 0) }

            VK_Q { rotate(0.001) }
            VK_E { rotate(-0.001) }

            VK_R { zoom(1.001) }
            VK_F { zoom(0.999) }
        }
    }
}