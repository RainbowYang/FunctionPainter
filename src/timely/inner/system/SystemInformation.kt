package timely.inner.system

/**
 * 本类单例用来存贮和输出所有来自本系统的信息。
 *
 * @author Rainbow Yang
 */
object SystemInformation {
    var informations: ArrayList<String> = ArrayList()
    var start: Long = System.currentTimeMillis()

    var todo: (information: String) -> Unit = ::println

    init {
        println()
        println("Time\tProcess")
    }

    fun log(information: String) {
        val informationWithTime = (System.currentTimeMillis() - start).toString() + "\t\t" + information
        informations.add(informationWithTime)
        todo(informationWithTime)
    }

    fun initStartLog(information: String) = log("Start initing $information...")

    fun initEndLog(information: String) = log("End initing $information...")
}