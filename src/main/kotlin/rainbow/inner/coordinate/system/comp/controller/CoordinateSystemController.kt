package rainbow.inner.coordinate.system.comp.controller

import rainbow.inner.coordinate.system.CoordinateSystem
import rainbow.inner.coordinate.system.comp.controller.event.CoordinateSystemControlEvent

/**
 * 本类用于接受控制事件，并向其传递CoordinateSystem
 * @author Rainbow Yang
 */
interface CoordinateSystemController {
    fun accept(event: CoordinateSystemControlEvent)
}