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
    MainFrame(CartesianCoordinateSystem(2).apply {
        move(200, 200)
    })
}

class MainFrame(var coordinateSystem: CoordinateSystem) : JFrame() {
    init {
        setSize(1000, 500)
        center(this)

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

    fun center(frame: Frame) {
        frame.setLocation((toolkit.screenSize.width - frame.width) / 2, (toolkit.screenSize.height - frame.height) / 2)
    }


}