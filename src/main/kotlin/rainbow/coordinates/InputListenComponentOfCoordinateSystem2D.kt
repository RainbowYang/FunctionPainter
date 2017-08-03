package rainbow.coordinates

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import rainbow.component.InputListenComponent
import rainbow.point.Point2D
import rainbow.utils.getDiffAngle
import rainbow.utils.moveTo
import java.awt.event.MouseEvent
import java.awt.event.MouseWheelEvent

open class InputListenComponentOfCoordinateSystem2D(val system: CoordinateSystem2D) : InputListenComponent() {
    lateinit var firstEvent: MouseEvent
    lateinit var lastEvent: MouseEvent

    @Expose @SerializedName("Zoom Speed") var zoomSpeed = 1.1

    override fun mousePressed(e: MouseEvent) {
        firstEvent = e
        lastEvent = e
    }

    override fun mouseDragged(e: MouseEvent) = with(system) {
        when (firstEvent.button) {
            MouseEvent.BUTTON1 -> move(e.x - lastEvent.x, e.y - lastEvent.y)
            MouseEvent.BUTTON3 -> rotate(getDiffAngle(lastEvent, e))
        }
        lastEvent = e

        repaint()
    }

    override fun mouseWheelMoved(e: MouseWheelEvent) = with(system) {
        val now = toCoordinatePoint(Point2D(e))

        moveTo(now)
        zoom(Math.pow(zoomSpeed, e.wheelRotation.toDouble()))
        moveTo(-now)

        repaint()
    }
}