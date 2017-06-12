package rainbow.outer.frame

import rainbow.inner.system.*
import rainbow.outer.frame.tool.FrameLocationSetter.center
import java.awt.Graphics
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

/**
 * @author Rainbow Yang
 */
class MainFrame : JFrame() {
    init {
        MySystem.setTitle(this)
        MySystem.setSize(this)
        center(this)

        SystemListeners.setListeners(this)

        SystemPainters.repainter = this::repaint
        add(object : JPanel() {
            override fun paintComponent(g: Graphics) {
                SystemPainters.paint(g)
            }
        })

        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isVisible = true
    }
}