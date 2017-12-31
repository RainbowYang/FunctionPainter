package rainbow.component.input.key

/**
 * 用于存贮一个对应于[Key]的事件操作
 * @author Rainbow Yang
 */
class KeyHandle(
        val description: String = "no description",
        var trigger: Key,
        /** 小于这个时间将无法被触发,单位:ms */
        var minTime: Int = 0,
        private val handle: (Int) -> Unit
) {

    private var lastRunTime = 0L

    /**
     * 对当前输入的Key进行判断,然后或执行
     */
    fun runHandle(key: Key, time: Number) {
        if (checkTime() && checkKey(key)) {
            handle(time.toInt())
            lastRunTime = System.currentTimeMillis()
        }
    }

    private fun checkTime() = (System.currentTimeMillis() - lastRunTime) >= minTime
    private fun checkKey(key: Key) = this.trigger == key

    override fun toString(): String {
        return "InputHandle(description='$description', trigger=$trigger, minTime=$minTime)"
    }
}