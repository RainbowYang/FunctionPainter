package start

import rainbow.coordinate.function.CoordinateFunction
import rainbow.coordinate.function.pointfunction.Hypercube
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
    frame.functions.apply {
        add(Hypercube(3, 5.5).apply {
            this.coordinateSystem = coordinateSystem
            init()
        })
    }
}

class MainFrame(var coordinateSystem: CoordinateSystem,
                var functions: MutableList<CoordinateFunction> = mutableListOf()) : JFrame() {
    init {
        setSize(1000, 500)
        center(this)

        add(object : JPanel() {
            override fun paintComponent(g: Graphics) {
                (g as Graphics2D).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

                val size = this@MainFrame.size
                coordinateSystem.paint(g, size)
                functions.forEach {
                    it.paint(g, size)
                }
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

    fun center(frame: Frame) {
        frame.setLocation((toolkit.screenSize.width - frame.width) / 2, (toolkit.screenSize.height - frame.height) / 2)
    }


}