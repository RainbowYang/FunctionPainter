package rainbow.outer.frame

import rainbow.inner.system.*
import rainbow.inner.system.SystemPainters.repaint
import rainbow.outer.frame.tool.FrameLocationSetter.center
import java.awt.Graphics
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

/**
 * @author Rainbow Yang
 */
class MainFrame : JFrame() {
    companion object {
        var mainFrame: JFrame? = null
    }

    init {
        mainFrame = this

        MySystem.setTitle(this)
        MySystem.setSize(this)
        center(this)

//        SystemPainters.repainter = this::repaint

        SystemListeners.setListeners(this)

        addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent) {
                val d = e.component.size
                MySystem.width = d.width.toDouble()
                MySystem.height = d.height.toDouble()
                SystemPainters.repaint()
            }
        })

        add(object : JPanel() {
            override fun paintComponent(g: Graphics) {
                SystemPainters.paint(g)
            }
        })

        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isVisible = true
    }
}