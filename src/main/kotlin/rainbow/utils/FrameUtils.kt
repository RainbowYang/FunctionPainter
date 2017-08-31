package rainbow.utils

import java.awt.Component
import javax.swing.JFrame

/**
 * @author Rainbow Yang
 */
fun Component.center() = setLocation((screenWidth - width) / 2, (screenHeight - height) / 2)

fun buildJFrame(width: Number, height: Number) = JFrame().apply {
    setSize(width.toInt(), height.toInt())
    center()
    defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    isVisible = true
}