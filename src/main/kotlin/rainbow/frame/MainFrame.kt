package rainbow.frame

import rainbow.coordinates.CoordinateSystem
import rainbow.function.CoordinateFunction
import java.awt.Component
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.JFrame
import javax.swing.JPanel

/**
 * 主界面用于显示
 * @author Rainbow Yang
 */
class MainFrame(var coordinateSystem: CoordinateSystem,
                var functions: MutableList<CoordinateFunction> = mutableListOf()) : JFrame() {
    init {
        defaultInit()

        add(object : JPanel() {
            override fun paintComponent(g: Graphics) {
                g as Graphics2D
                coordinateSystem.paintImageTo(g)
                functions.forEach { it.paintImageTo(g) }
            }
        })

        addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                this@MainFrame.repaint()
            }
        })

        coordinateSystem.bindTo(this)

        repaint()
    }

    fun defaultInit() {
        setSize(1000, 500)
        center(this)
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }


    fun center(comp: Component) {
        val screenSize = toolkit.screenSize
        comp.setLocation((screenSize.width - comp.width) / 2, (screenSize.height - comp.height) / 2)
    }
}