package rainbow.component.input.key

import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.*
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
                VK_ALT -> isAltDown = true
                VK_SHIFT -> isShiftDown = true
                VK_CONTROL -> isCtrlDown = true
                else -> pressedKeys.add(code)
            }
        }

        override fun keyReleased(e: KeyEvent) {
            val code = e.keyCode
            when (code) {
                VK_ALT -> isAltDown = false
                VK_SHIFT -> isShiftDown = false
                VK_CONTROL -> isCtrlDown = false
                else -> pressedKeys.remove(code)
            }
        }
    }
}