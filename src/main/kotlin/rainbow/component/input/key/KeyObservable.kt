package rainbow.component.input.key

import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.util.*
import kotlin.concurrent.schedule

/**
 * 存贮当前的按键状况，并对符合的执行操作
 * @author Rainbow Yang
 */
class KeyObservable {

    /**
     * 尝试执行handle的周期,以毫秒(ms)为单位
     */
    var period: Int = 10

    private val pressedKeys = Collections.synchronizedSet(mutableSetOf<Int>())

    private var isAltDown = false
    private var isCtrlDown = false
    private var isShiftDown = false

    val handlesList = mutableListOf<KeyHandles>()

    fun addHandles(handles: KeyHandles) = handlesList.add(handles)

    fun startToRunHandles() {
        Timer().schedule(0, period.toLong()) {
            pressedKeys.forEach {
                val key = Key(it, isAltDown, isCtrlDown, isShiftDown)
                handlesList.forEach {
                    it.runHandle(key, period)
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
                else -> this@KeyObservable.pressedKeys.add(code)
            }
        }

        override fun keyReleased(e: KeyEvent) {
            val code = e.keyCode
            when (code) {
                KeyEvent.VK_ALT -> isAltDown = false
                KeyEvent.VK_SHIFT -> isShiftDown = false
                KeyEvent.VK_CONTROL -> isCtrlDown = false
                else -> this@KeyObservable.pressedKeys.remove(code)
            }
        }
    }
}