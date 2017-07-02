package rainbow.inner.coordinate.system.comp.controller.event

import rainbow.inner.coordinate.point.PointDouble
import rainbow.inner.coordinate.system.CoordinateSystem
import rainbow.inner.coordinate.system.moveTo
import java.awt.event.MouseWheelEvent

/**
 * @author Rainbow Yang
 */
class ZoomEvent(event: MouseWheelEvent) : CoordinateSystemControlEvent({
    val nowLocation = it.locationChanger.toSystem(PointDouble(event.x, event.y))
    it.moveTo(nowLocation)
    it.axes.allLengthTimes *= Math.pow(zoomSpeed, event.wheelRotation.toDouble())
    it.moveTo(-nowLocation)
}
) {
    companion object {
        val zoomSpeed = 1.1
    }
}
