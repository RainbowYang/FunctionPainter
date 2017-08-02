package rainbow.coordinates

import rainbow.component.InputListenComponent
import rainbow.point.Point2D
import rainbow.utils.asPoint2D
import rainbow.utils.getDiffAngle
import rainbow.utils.moveTo
import java.awt.event.MouseEvent
import java.awt.event.MouseWheelEvent

/**
 * 二维坐标系的接口
 * 实现平移，旋转，伸缩
 * @author Rainbow Yang
 */
abstract class CoordinateSystem2D(
        var x: Double = 0.0,
        var y: Double = 0.0,
        var rotatedAngle: Double = 0.0,
        var zoomRate: Double = 1.0
) : CoordinateSystem() {

    override var inputComponent: InputListenComponent = CoordinateSystem2DInputListener(this)

    var rotatedAngleAsDegree: Double
        get() = Math.toDegrees(rotatedAngle)
        set(value) {
            rotatedAngle = Math.toRadians(value)
        }

    fun move(x: Number, y: Number) {
        this.x += x.toDouble()
        this.y += y.toDouble()
    }

    fun moveTo(x: Number, y: Number) {
        this.x = x.toDouble()
        this.y = y.toDouble()
    }

    fun rotate(angle: Number) {
        rotatedAngle += angle.toDouble()
    }

    fun zoom(times: Number) {
        zoomRate *= times.toDouble()
    }

    fun Point2D.rotateAndScaleAndMove(): Point2D {
        val result = (this.spin(rotatedAngle) * zoomRate).asPoint2D

        return Point2D(x + result.x, y - result.y)
    }

    fun Point2D.inverseRotateAndScaleAndMove() =
            (Point2D(this.x - x, y - this.y).spin(-rotatedAngle) / zoomRate).asPoint2D

    open class CoordinateSystem2DInputListener(val coordinateSystem: CoordinateSystem2D) : InputListenComponent() {
        lateinit var firstEvent: MouseEvent
        lateinit var lastEvent: MouseEvent

        var zoomSpeed = 1.1

        override fun mousePressed(e: MouseEvent) {
            firstEvent = e
            lastEvent = e
        }

        override fun mouseDragged(e: MouseEvent) = with(coordinateSystem) {
            when (firstEvent.button) {
                MouseEvent.BUTTON1 -> move(e.x - lastEvent.x, e.y - lastEvent.y)
                MouseEvent.BUTTON3 -> rotate(getDiffAngle(lastEvent, e))
            }
            lastEvent = e

            repaint()
        }


        override fun mouseWheelMoved(e: MouseWheelEvent) = with(coordinateSystem) {
            val now = toCoordinatePoint(Point2D(e))

            moveTo(now)
            zoom(Math.pow(zoomSpeed, e.wheelRotation.toDouble()))
            moveTo(-now)

            repaint()
        }
    }
}