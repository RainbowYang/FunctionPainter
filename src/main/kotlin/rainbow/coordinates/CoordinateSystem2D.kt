package rainbow.coordinates

import rainbow.component.InputListenComponent
import rainbow.point.PointDouble
import rainbow.utils.getDiffAngle
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

    //default
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

    fun rotate(angle: Number) {
        rotatedAngle += angle.toDouble()
    }

    fun zoom(times: Number) {
        zoomRate *= times.toDouble()
    }

    fun rotateAndScaleAndMove(pd: PointDouble): PointDouble {
        var result = pd
        if (rotatedAngle != 0.0)
            result = result.spin(rotatedAngle)

        if (zoomRate != 1.0)
            result = result.times(zoomRate)

        result.x = x + result.x
        // 屏幕的点是向右下角递增,而坐标点是向右上角递增
        result.y = y - result.y

        return result
    }

    fun inverseRotateAndScaleAndMove(pd: PointDouble): PointDouble {
        var result = pd
        result.x = result.x - x
        // 屏幕的点是向右下角递增,而坐标点是向右上角递增
        result.y = y - result.y

        if (rotatedAngle != 0.0)
            result = result.spin(-rotatedAngle)

        if (zoomRate != 1.0)
            result = result.times(1.0 / zoomRate)
        return result
    }

    protected open class CoordinateSystem2DInputListener(val coordinateSystem: CoordinateSystem2D) : InputListenComponent() {
        lateinit var firstEvent: MouseEvent
        lateinit var lastEvent: MouseEvent

        override fun mousePressed(e: MouseEvent) {
            firstEvent = e
            lastEvent = e
        }

        override fun mouseDragged(e: MouseEvent) {
            when (firstEvent.button) {
                MouseEvent.BUTTON1 -> coordinateSystem.move(e.x - lastEvent.x, e.y - lastEvent.y)
                MouseEvent.BUTTON3 -> coordinateSystem.rotate(coordinateSystem.getDiffAngle(lastEvent, e))
            }
            lastEvent = e
        }

        override fun mouseWheelMoved(e: MouseWheelEvent) {
            coordinateSystem.zoom(Math.pow(1.1, e.wheelRotation.toDouble()))
        }
    }
}