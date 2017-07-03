package rainbow.inner.coordinate.system.command

import rainbow.inner.coordinate.system.CoordinateSystem
import java.awt.event.MouseEvent

/**
 * 用于对CoordinateSystem进行旋转
 * @author Rainbow Yang
 */
class RotateCommand(var rotationAngle: Double) : CoordinateSystemCommand<CoordinateSystem>() {

    private var start: MouseEvent? = null
    private var end: MouseEvent? = null

    constructor(start: MouseEvent, end: MouseEvent) : this(0.0) {
        this.start = start
        this.end = end
    }

    override fun todo(system: CoordinateSystem) {
        if (start != null && end != null) {
            system.rotateAngle += getDiffAngle(system, start!!, end!!)
        } else {
            system.rotateAngle += rotationAngle
        }
    }

    //得到两个MouseEvent分别关于CoordinateSystem原点的角度之差,用于旋转
    fun getDiffAngle(cs: CoordinateSystem, event1: MouseEvent, event2: MouseEvent): Double {
        return Math.atan2(event2.y - cs.y, event2.x - cs.x) - Math.atan2(event1.y - cs.y, event1.x - cs.x)
    }

    override fun toString(): String {
        return "RotateCommand(rotationAngle=$rotationAngle)"
    }


}