package rainbow.frame

import rainbow.ables.addListener
import rainbow.coordinate.System
import java.awt.Graphics
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.JFrame
import javax.swing.JPanel

/**
 * @author Rainbow Yang
 */
class MainFrame(var system: System) : JFrame() {

    init {
        setSize(1000, 500)
        center()

        add(object : JPanel() {
            override fun paintComponent(g: Graphics) {
                system.paintImage(g)
            }
        })

        addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                val size = this@MainFrame.size
                system.repaint(size.width, size.height, this@MainFrame::repaint)
            }
        })

        addListener(system.coordinateSystem) {
            system.repaint(size.width, size.height, this@MainFrame::repaint)
        }

        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }

    fun center() {
        setLocation((toolkit.screenSize.width - width) / 2, (toolkit.screenSize.height - height) / 2)
    }
}