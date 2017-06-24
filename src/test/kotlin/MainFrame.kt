import com.sun.glass.ui.Size
import rainbow.inner.background.Background
import rainbow.inner.coordinate.system.CoordinateSystem
import java.awt.Graphics
import javax.swing.JFrame
import javax.swing.JPanel

/**
 * @author Rainbow Yang
 */
class MainFrame(var background: Background,
                var coordinateSystem: CoordinateSystem) : JFrame() {
    init {
        setSize(1000, 500)

        add(object : JPanel() {
            override fun paintComponent(g: Graphics) {
                this@MainFrame.background.paint(g, this@MainFrame.width, this@MainFrame.height)
                coordinateSystem.painter.paint(g)
                this@MainFrame.repaint()
            }
        })

        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }
}