package timely

import rainbow.inner.painter.background.DefaultBackgroundPainter
import rainbow.inner.painter.coordinate_system.DefaultCoordinateSystemPainter
import rainbow.inner.painter.function.DefaultFunctionsPainter
import rainbow.inner.system.SystemPainters

/**
 * @author Rainbow Yang
 */
fun main(args: Array<String>) {
    addPainters()


}

fun addPainters() {
    SystemPainters.painters.apply {
        add(DefaultBackgroundPainter())
        add(DefaultCoordinateSystemPainter())
        add(DefaultFunctionsPainter())
    }
}
