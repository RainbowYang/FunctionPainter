package rainbow.inner.coordinate.system.comp.controller.event

import rainbow.inner.coordinate.system.CoordinateSystem
import java.awt.event.MouseEvent

/**
 * 二维旋转
 * @author Rainbow Yang
 */
class RotateEvent(event1: MouseEvent, event2: MouseEvent) :
        CoordinateSystemControlEvent
        ({ it.axes.startAngle += getDiffAngle(it, event1, event2) })

fun getDiffAngle(cs: CoordinateSystem, event1: MouseEvent, event2: MouseEvent): Double {
    return Math.atan2(event2.y - cs.y, event2.x - cs.x) - Math.atan2(event1.y - cs.y, event1.x - cs.x)
}
