package timely

import timely.inner.painter.background.DefaultBackgroundPainter
import timely.inner.painter.coordinate_system.DefaultCoordinateSystemPainter
import timely.inner.painter.function.DefaultFunctionsPainter
import timely.inner.system.SystemPainters

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
