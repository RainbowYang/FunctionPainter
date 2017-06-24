import rainbow.inner.background.Background
import rainbow.inner.coordinate.system.CoordinateSystemForAxes

/**
 * @author Rainbow Yang
 */
fun main(args: Array<String>) {
    val background = Background("0x2B2B2B")
    println(background)

    val cs = CoordinateSystemForAxes(3)

    MainFrame(background, cs)
}