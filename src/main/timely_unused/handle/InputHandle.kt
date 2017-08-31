package rainbow.input.handle

import java.awt.event.InputEvent

/**
 * 确定了一种特定的鼠标或键盘的操作方式
 * 以及
 * 在这种操作方式被执行后所要执行的操作
 *
 * @author Rainbow Yang
 */
abstract class InputHandle(
        var needAlt: Boolean = false,
        var needCtrl: Boolean = false,
        var needShift: Boolean = false
) {

    /**
     * 在执行handle之前，先用[isRightInput]进行判断，然后决定是否执行
     *
     * @param event 产生的鼠标或键盘事件
     * @return 成功执行则返回true，否则返回false
     */
    fun runHandleWhenIsRightInput(event: InputEvent) =
            if (isRightInput(event)) {
                runHandle(event)
                true
            } else false


    /**
     *  用于判断一个[InputEvent]是否符合当前所要求的操作方式
     *
     *  子类应重写此方法，并先调用[isAltAndCtrlAndShiftRight]
     *
     *  @param event 产生的鼠标或键盘事件
     *  @return 如符合要求，则返回true; 否则返回false
     */
    abstract fun isRightInput(event: InputEvent): Boolean

    /**
     * 根据所接受的[InputEvent]进行执行操作
     * 在执行之前应该先用[isRightInput]进行判断
     *
     * @param event 产生的鼠标或键盘事件
     */
    abstract fun runHandle(event: InputEvent)

    /**
     * 判断[InputEvent]对于[needAlt]，[needCtrl]，[needShift]是否符合
     */
    protected fun isAltAndCtrlAndShiftRight(event: InputEvent) =
            with(event) { isAltDown == needAlt && isShiftDown == needShift && isControlDown == needCtrl }

}