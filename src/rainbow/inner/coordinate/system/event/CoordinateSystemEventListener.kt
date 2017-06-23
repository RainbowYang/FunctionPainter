package rainbow.inner.coordinateSystem.system.event

import rainbow.inner.coordinate.point.PointDouble
import rainbow.inner.coordinate.system.event.CoordinateSystemEvent
import rainbow.inner.coordinate.system.event.MoveEvent
import rainbow.inner.coordinate.system.event.RotateEvent
import rainbow.inner.coordinate.system.event.ZoomEvent
import rainbow.inner.coordinate.system.move
import rainbow.inner.coordinate.system.moveTo
import rainbow.inner.coordinate.system.moveToOpposite
import rainbow.inner.system.MySystem
import rainbow.inner.system.SystemPainters
import rainbow.outer.frame.MainFrame
import kotlin.coroutines.experimental.EmptyCoroutineContext.plus

/**
 * @author Rainbow Yang
 */
interface CoordinateSystemEventListener {
    companion object {
        var zoomSpeed = 1.1
    }

    fun accept(event: CoordinateSystemEvent) {
        if (event is MoveEvent) {
            move(event)
        } else if (event is RotateEvent) {
            rotate(event)
        } else if (event is ZoomEvent) {
            zoom(event)
        }
        SystemPainters.repaint()
    }


    fun move(event: MoveEvent) {
        MySystem.coordinateSystem.move(event.dx, event.dy)
    }

    fun zoom(event: ZoomEvent) {
        val p = MySystem.coordinateSystem.toSystem(PointDouble(event.x, event.y))
        MySystem.coordinateSystem.moveTo(p)
        MySystem.coordinateSystem.axes.allLengthTimes *= (Math.pow(zoomSpeed, event.zoomLevel))
        MySystem.coordinateSystem.moveToOpposite(p)
    }

    fun rotate(event: RotateEvent)
}
