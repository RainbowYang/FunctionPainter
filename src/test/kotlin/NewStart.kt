import rainbow.inner.background.Background
import rainbow.inner.coordinate.system.CoordinateSystemForAxes
import rainbow.inner.coordinate.system.comp.controller.event.MoveEvent

/**
 * @author Rainbow Yang
 */
fun main(args: Array<String>) {
    val background = Background("0x2B2B2B")

    val cs = CoordinateSystemForAxes(3)

    cs.controller.accept(MoveEvent(1000, 1000))

    MainFrame(background, cs)
}