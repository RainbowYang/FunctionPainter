package rainbow.inner.coordinate.system.command

import rainbow.inner.coordinate.system.CoordinateSystem
import java.awt.event.MouseWheelEvent

/**
 * 用于对CoordinateSystem进行缩放
 * @author Rainbow Yang
 */
class ZoomCommand(val zoomTimes: Double) : CoordinateSystemCommand<CoordinateSystem>() {
    companion object {
        val zoomSpeed = 1.1
    }

    constructor(mouseWheelEvent: MouseWheelEvent) : this(Math.pow(zoomSpeed, mouseWheelEvent.wheelRotation.toDouble()))

    override fun todo(system: CoordinateSystem) {
        system.zoomRate *= zoomTimes
    }
}