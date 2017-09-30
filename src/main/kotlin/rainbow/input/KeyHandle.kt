package rainbow.input

/**
 * @author Rainbow Yang
 */
class KeyHandle(var key: Int,
                var needAlt: Boolean = false,
                var needCtrl: Boolean = false,
                var needShift: Boolean = false,
                var handle: (Double) -> Unit) {

    fun runHandle(time: Number) = handle(time.toDouble())

    fun isRightInput(key: Int, isAltDown: Boolean, isCtrlDown: Boolean, isShiftDown: Boolean) =
            this.key == key && isAltAndCtrlAndShiftRight(isAltDown, isCtrlDown, isShiftDown)

    private fun isAltAndCtrlAndShiftRight(isAltDown: Boolean, isCtrlDown: Boolean, isShiftDown: Boolean) =
            isAltDown == needAlt && isCtrlDown == needCtrl && isShiftDown == needShift

}