import rainbow.inner.coordinate.system.CoordinateSystem
import rainbow.inner.coordinate.system.cartesian.CartesianCoordinateSystem
import rainbow.inner.painter.coordinates.PainterOfCartesianCoordinateSystem
import java.awt.Graphics
import javax.swing.JFrame
import javax.swing.JPanel

/**
 * @author Rainbow Yang
 */
class MainFrame(var coordinateSystem: CoordinateSystem) : JFrame() {
    init {
        setSize(1000, 500)

        add(object : JPanel() {
            override fun paintComponent(g: Graphics) {
                // todo test
                PainterOfCartesianCoordinateSystem(coordinateSystem as CartesianCoordinateSystem).paint(g)
                this@MainFrame.repaint()
            }
        })

        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }
}