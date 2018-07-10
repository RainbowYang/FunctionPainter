package rainbow.component.input.key

/**
 * 用于存贮一个对应于[Key]的事件操作
 *
 * 如需按键为执行一次的，可以将[minTime]设置为200ms左右，以防止被多次执行
 * @author Rainbow Yang
 */
class KeyHandle(
        val description: String = "no description",
        var trigger: Key,
        /** 小于这个时间将无法被触发,单位:ms */
        var minTime: Int = HOLDING,
        private val handle: (Double) -> Unit
) {

    companion object {
        /**表示持续执行*/
        const val HOLDING = 0
        /**表示单次执行，设置200ms防止多次执行*/
        const val ONCE = 200
    }

    private var lastRunTime = 0L

    /**
     * 对当前输入的Key进行判断,然后或执行
     */
    fun runHandle(key: Key, time: Number) {
        if (checkTime() && checkKey(key)) {
            handle(time.toInt() / 1000.0)
            lastRunTime = System.currentTimeMillis()
        }
    }

    private fun checkTime() = (System.currentTimeMillis() - lastRunTime) >= minTime
    private fun checkKey(key: Key) = this.trigger == key

    override fun toString(): String {
        return "InputHandle(description='$description', trigger=$trigger, minTime=$minTime)"
    }
}