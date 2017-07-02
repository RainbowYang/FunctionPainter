package rainbow.inner.coordinate.system.comp.controller

import rainbow.inner.coordinate.system.CoordinateSystem
import rainbow.inner.coordinate.system.CoordinateSystemForAxes
import rainbow.inner.coordinate.system.comp.controller.event.CoordinateSystemControlEvent

/**
 * @author Rainbow Yang
 */
class ControllerOfCoordinateSystemForAxes(val coordinateSystemForAxes: CoordinateSystemForAxes)
    : CoordinateSystemController {
    override fun accept(event: CoordinateSystemControlEvent) = event.todo(coordinateSystemForAxes)
}