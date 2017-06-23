package rainbow.inner.listener

import javafx.scene.input.MouseButton
import java.awt.Event
import java.awt.Frame
import java.awt.event.*

/**
 * 所有希望从SystemListeners得到函数调用的接口
 * @author Rainbow Yang
 */
interface SystemListener {
}

/**
 * 所有希望从SystemListeners得到函数调用的接口，当发生鼠标事件时
 * @author Rainbow Yang
 */
class SystemMouseListener(var event: String, var handler: (MouseEvent) -> Unit) : SystemListener {
    fun getListener(): MouseAdapter = when (event) {
        else -> object : MouseAdapter() {}
    }
}

class SystemMouseMotionListener(var event: String, var handler: (MouseEvent, MouseEvent) -> Unit) : SystemListener {
    fun getListener(): MouseAdapter = when (event) {
        "左键拖动" -> object : MouseAdapter() {
            override fun mouseDragged(e: MouseEvent?) {
                if (e?.button == MouseEvent.BUTTON1)
                    handler(e, e)
            }
        }
        "右键拖动" -> object : MouseAdapter() {
            override fun mouseDragged(e: MouseEvent?) {
                if (e?.button == MouseEvent.BUTTON3)
                    handler(e, e)
            }
        }
        else -> object : MouseAdapter() {}
    }
}

class SystemMouseWheelListener(var handler: (MouseWheelEvent) -> Unit) : SystemListener {
    fun getListener(): MouseAdapter = object : MouseAdapter() {
        override fun mouseWheelMoved(e: MouseWheelEvent?) {
            handler(e!!)
        }
    }
}

/**
 * @author Rainbow Yang
 */
class SystemKeyListener(var event: String, var handler: (KeyEvent) -> Unit) : SystemListener {
    fun getListener(): KeyAdapter {
        return object : KeyAdapter() {

        }
    }
}