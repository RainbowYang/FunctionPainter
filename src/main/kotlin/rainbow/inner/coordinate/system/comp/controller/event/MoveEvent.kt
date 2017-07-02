package rainbow.inner.coordinate.system.comp.controller.event

import rainbow.inner.coordinate.point.PointDouble
import rainbow.inner.coordinate.system.CoordinateSystem
import rainbow.inner.coordinate.system.move
import java.awt.event.MouseEvent

/**
 * 移动事件
 * @author Rainbow Yang
 */
class MoveEvent(dx: Double, dy: Double) : CoordinateSystemControlEvent({ it.move(dx, dy) }) {

    constructor(dx: Int, dy: Int) : this(dx.toDouble(), dy.toDouble())

    constructor(startPoint: PointDouble, endPoint: PointDouble) :
            this(endPoint.x - startPoint.x, endPoint.y - startPoint.y)

    constructor(event1: MouseEvent, event2: MouseEvent) :
            this(event2.x - event1.x, event2.y - event1.y)
}