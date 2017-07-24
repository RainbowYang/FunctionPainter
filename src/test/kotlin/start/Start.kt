package start

import rainbow.ables.addListener
import rainbow.coordinate.function.CoordinateFunction
import rainbow.coordinate.system.CoordinateSystem
import rainbow.coordinate.system.cartesian.CartesianCoordinateSystem
import java.awt.Frame
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.JFrame
import javax.swing.JPanel

fun main(args: Array<String>) {
    val coordinateSystem = CartesianCoordinateSystem(3)
    val frame = MainFrame(coordinateSystem)
}

class MainFrame(var coordinateSystem: CoordinateSystem,
                var functions: MutableList<CoordinateFunction> = mutableListOf()) : JFrame() {
    init {
        setSize(1000, 500)
        center(this)

        add(object : JPanel() {
            override fun paintComponent(g: Graphics) {
                (g as Graphics2D).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

                coordinateSystem.paintImage(g)
                functions.forEach { it.paintImage(g) }
            }
        })

        addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                val size = this@MainFrame.size
                coordinateSystem.repaint(size.width, size.height, this@MainFrame::repaint)
                functions.forEach { it.repaint(size.width, size.height, this@MainFrame::repaint) }
            }
        })

        addListener(coordinateSystem) {
            coordinateSystem.repaint(size.width, size.height, this@MainFrame::repaint)
            functions.forEach { it.repaint(size.width, size.height, this@MainFrame::repaint) }
        }

        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }

    fun center(frame: Frame) {
        frame.setLocation((toolkit.screenSize.width - frame.width) / 2, (toolkit.screenSize.height - frame.height) / 2)
    }
}