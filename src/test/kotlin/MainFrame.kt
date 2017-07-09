import rainbow.coordinate.system.CoordinateSystem
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.JFrame
import javax.swing.JPanel


/**
 * 测试
 * @author Rainbow Yang
 */
class MainFrame(var coordinateSystem: CoordinateSystem) : JFrame() {
    init {
        setSize(1000, 500)

        add(object : JPanel() {
            override fun paintComponent(g: Graphics) {
                (g as Graphics2D).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
                coordinateSystem.paint(g, this@MainFrame.size)
            }
        })

        addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                this@MainFrame.repaint()
            }
        })

        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }


}