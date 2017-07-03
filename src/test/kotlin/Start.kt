import rainbow.inner.coordinate.system.cartesian.CartesianCoordinateSystem
import rainbow.inner.coordinate.system.command.RotateCommand

/**
 * @author Rainbow Yang
 */
fun main(args: Array<String>) {
    val cs = CartesianCoordinateSystem(3)
    cs.doCommand(RotateCommand(1.0))
    MainFrame(cs)
}