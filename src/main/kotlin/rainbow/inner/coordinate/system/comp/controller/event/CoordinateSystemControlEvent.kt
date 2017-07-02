package rainbow.inner.coordinate.system.comp.controller.event

import rainbow.inner.coordinate.system.CoordinateSystem

/**
 * 控制事件
 * @author Rainbow Yang
 */
open class CoordinateSystemControlEvent(val todo: (CoordinateSystem) -> Unit)