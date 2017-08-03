package rainbow.coordinates

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
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
abstract class CoordinateSystem2D : CoordinateSystem() {

    abstract var x: Double
    abstract var y: Double
    abstract var rotatedAngle: Double
    abstract var zoomRate: Double

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

    fun Point2D.rotateAndScaleAndMove(): Point2D = with(this@CoordinateSystem2D) {
        val result = (spin(rotatedAngle) * zoomRate).asPoint2D

        return Point2D(this.x + result.x, this.y - result.y)
    }

    fun Point2D.inverseRotateAndScaleAndMove(): Point2D {
        val system = this@CoordinateSystem2D
        val result = Point2D(this.x - system.x, system.y - this.y).spin(-rotatedAngle) / zoomRate
        return result.asPoint2D
    }

    open class CoordinateSystem2DInputListener(val coordinateSystem: CoordinateSystem2D) : InputListenComponent() {
        lateinit var firstEvent: MouseEvent
        lateinit var lastEvent: MouseEvent

        @Expose @SerializedName("Zoom Speed") var zoomSpeed = 1.1

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