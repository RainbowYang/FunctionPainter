package rainbow.coordinate.function.mathfunction

import rainbow.coordinate.function.PointsFunction
import rainbow.coordinate.point.CoordinatePoint
import rainbow.coordinate.point.PointForAxes

typealias DoubleUnaryOperator = (Double) -> Double

/**
 * 本类由各个维度上的Function来控制。
 * 并自动根据取值范围生成点。
 * @author Rainbow Yang
 */
open class MathFunction(initFunctions: List<DoubleUnaryOperator> = listOf()) : PointsFunction() {
    companion object {
        val SELF: DoubleUnaryOperator = { it }

        operator fun invoke(vararg initFunctions: DoubleUnaryOperator) = MathFunction(initFunctions.asList())
    }

    //每组Function的集合
    private val functionsList = mutableListOf<
            List<DoubleUnaryOperator>
            >()

    init {
        if (initFunctions.isNotEmpty()) functionsList.add(initFunctions)
    }

    protected var start = -20.0
    protected var end = 20.0
    protected var step = 0.01

    //生成所有的点
    override fun calcPoints() {
        for (functions in functionsList) {
            if (functions.isEmpty()) continue

            newPointList()

            var index = start
            while (index <= end) {

                val values = DoubleArray(functions.size)
                values.indices.forEach { values[it] = functions[it](index) }
                addPoint(createPoint(values))

                index += step
            }
        }
    }

    /**
     * 设置functions，会先清空functionsList
     */
    protected fun setFunctions(functions: List<DoubleUnaryOperator>) {
        this.functionsList.clear()
        addFunctions(functions)
    }

    /**
     * 设置functions，会先清空functionsList
     */
    protected fun setFunctions(vararg functions: DoubleUnaryOperator) = setFunctions(functions.asList())

    protected fun addFunctions(functions: List<DoubleUnaryOperator>) = functionsList.add(functions)
    protected fun addFunctions(vararg functions: DoubleUnaryOperator) = addFunctions(functions.asList())


    /**
     * 需要创建的点,默认为PointForAxes
     * 子类如需创造不是坐标系的点，只要重写此方法即可。
     */
    protected open fun createPoint(values: DoubleArray): CoordinatePoint = PointForAxes(values)

    fun setRange(start: Double, end: Double, step: Double) {
        this.start = start
        this.end = end
        this.step = step
    }

    override fun toString(): String {
        return "MathFunction(functionsList=$functionsList, start=$start, end=$end, step=$step)"
    }
}
