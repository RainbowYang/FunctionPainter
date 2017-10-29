package rainbow.component.input

import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.util.*
import kotlin.concurrent.schedule

/**
 * 存贮当前的按键状况，并对符合的执行操作
 * @author Rainbow Yang
 */
class KeyMap {

    //多少 ms 执行一次
    var period: Number = 10

    val pressedKeys = Collections.synchronizedSet(mutableSetOf<Int>())

    var isAltDown = false
    var isCtrlDown = false
    var isShiftDown = false

    val handles = mutableListOf<KeyHandle>()

    fun addHandle(handle: KeyHandle) = handles.add(handle)

    operator fun Int.invoke(isAltDown: Boolean = false,
                            isCtrlDown: Boolean = false,
                            isShiftDown: Boolean = false,
                            handle: (Double) -> Unit) =
            addHandle(KeyHandle(this, isAltDown, isCtrlDown, isShiftDown, handle))

    fun startToRunHandles() {
        Timer().schedule(0, period.toLong()) {
            pressedKeys.forEach { key ->
                handles.forEach { handle ->
                    if (handle.isRightInput(key, isAltDown, isCtrlDown, isShiftDown)) {
                        handle.runHandle(period.toInt() / 1000.0)
                    }
                }
            }
        }
    }

    fun getListener() = object : KeyAdapter() {
        override fun keyPressed(e: KeyEvent) {
            val code = e.keyCode
            when (code) {
                KeyEvent.VK_ALT -> isAltDown = true
                KeyEvent.VK_SHIFT -> isShiftDown = true
                KeyEvent.VK_CONTROL -> isCtrlDown = true
                else -> this@KeyMap.pressedKeys.add(code)
            }
        }

        override fun keyReleased(e: KeyEvent) {
            val code = e.keyCode
            when (code) {
                KeyEvent.VK_ALT -> isAltDown = false
                KeyEvent.VK_SHIFT -> isShiftDown = false
                KeyEvent.VK_CONTROL -> isCtrlDown = false
                else -> this@KeyMap.pressedKeys.remove(code)
            }
        }
    }
}