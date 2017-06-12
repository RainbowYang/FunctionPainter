package rainbow.inner.system

import java.awt.Frame
import java.awt.event.MouseAdapter

/**
 * 本类用于存贮所有的监听器
 * @author Rainbow Yang
 */
object SystemListeners {
    val mouseListeners = mutableListOf<MouseAdapter>()

    fun addMouseListener(l: MouseAdapter) {
        mouseListeners.add(l)
    }

    fun setListeners(frame: Frame) {
        mouseListeners.forEach {
            frame.addMouseListener(it)
            frame.addMouseMotionListener(it)
            frame.addMouseWheelListener(it)
        }
    }
}