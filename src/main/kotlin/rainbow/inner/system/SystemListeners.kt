package rainbow.inner.system

import rainbow.inner.listener.CoordinateSystemListener
import rainbow.inner.system.SystemPainters.repaint
import java.awt.Dimension
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.MouseAdapter
import javax.swing.JFrame

/**
 * 本类用于存贮所有的监听器
 * @author Rainbow Yang
 */
object SystemListeners {
    //    val mouseListeners = mutableListOf<SystemMouseListener>()
//    val mouseMotionListener = mutableListOf<SystemMouseMotionListener>()
//    val mouseWheelListener = mutableListOf<SystemMouseWheelListener>()
//    val keyListeners = mutableListOf<SystemKeyListener>()
//
//    fun addListener(l: SystemMouseListener) = mouseListeners.add(l)
//    fun addListener(l: SystemMouseMotionListener) = mouseMotionListener.add(l)
//    fun addListener(l: SystemMouseWheelListener) = mouseWheelListener.add(l)
//    fun addListener(l: SystemKeyListener) = keyListeners.add(l)
//
//    fun setListeners(frame: Frame) {
//        mouseListeners.forEach { frame.addMouseListener(it.getListener()) }
//        mouseMotionListener.forEach { frame.addMouseMotionListener(it.getListener()) }
//        mouseWheelListener.forEach { frame.addMouseWheelListener(it.getListener()) }
//        keyListeners.forEach { frame.addKeyListener(it.getListener()) }
//    }
    val mouseAdapters = mutableListOf<MouseAdapter>()

    fun addMouseListener(l: MouseAdapter) {
        mouseAdapters.add(l)
    }

    fun setListeners(frame: JFrame) {
        mouseAdapters.forEach {
            frame.addMouseListener(it)
            frame.addMouseMotionListener(it)
            frame.addMouseWheelListener(it)
        }
    }
}