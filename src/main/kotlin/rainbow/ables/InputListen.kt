package rainbow.ables

import java.awt.Component
import java.awt.event.*

/**
 * 用于接受[MouseEvent]和[KeyEvent]
 * @author Rainbow Yang
 */

interface InputListenable {
    var listener: InputListener

    fun addListener(component: Component, callback: () -> Unit) {
        listener.callback = callback
        component.apply {
            addMouseListener(listener)
            addMouseMotionListener(listener)
            addMouseWheelListener(listener)
            addKeyListener(listener)
        }
    }

}

fun Component.addListener(listenable: InputListenable, callback: () -> Unit) = listenable.addListener(this, callback)

abstract class InputListener : MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

    var callback = {}

    override fun mouseReleased(e: MouseEvent) {}
    override fun mouseEntered(e: MouseEvent) {}
    override fun mouseClicked(e: MouseEvent) {}
    override fun mouseExited(e: MouseEvent) {}
    override fun mousePressed(e: MouseEvent) {}

    override fun mouseMoved(e: MouseEvent) {}
    override fun mouseDragged(e: MouseEvent) {}

    override fun mouseWheelMoved(e: MouseWheelEvent) {}

    override fun keyTyped(e: KeyEvent) {}
    override fun keyPressed(e: KeyEvent) {}
    override fun keyReleased(e: KeyEvent) {}
}

object EmptyListener : InputListener()
