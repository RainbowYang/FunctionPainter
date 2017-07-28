package rainbow.frame

import rainbow.coordinates.CoordinateSystem
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.JFrame
import javax.swing.JPanel

/**
 * @author Rainbow Yang
 */
class MainFrame(var system: CoordinateSystem) : JFrame() {

    init {
        setSize(1000, 500)
        center()

        add(object : JPanel() {
            override fun paintComponent(g: Graphics) {
                system.paintImageTo(g as Graphics2D)
            }
        })

        addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                this@MainFrame.repaint()
            }
        })

        system.bindTo(this)

        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }

    fun center() {
        setLocation((toolkit.screenSize.width - width) / 2, (toolkit.screenSize.height - height) / 2)
    }
}