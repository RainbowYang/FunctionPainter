package rainbow.inner.coordinate.system.command

import rainbow.inner.coordinate.system.CoordinateSystem
import java.awt.event.MouseEvent

/**
 * 用于对CoordinateSystem进行移动
 * @author Rainbow Yang
 */
class MoveCommand(val dx: Double, val dy: Double) : CoordinateSystemCommand<CoordinateSystem>() {
    constructor(dx: Int, dy: Int) : this(dx.toDouble(), dy.toDouble())
    constructor(start: MouseEvent, end: MouseEvent) : this(end.x - start.x, end.y - start.y)

    override fun todo(system: CoordinateSystem) {
        system.x += dx
        system.y += dy
    }

    override fun toString(): String {
        return "MoveCommand(dx=$dx, dy=$dy)"
    }

}