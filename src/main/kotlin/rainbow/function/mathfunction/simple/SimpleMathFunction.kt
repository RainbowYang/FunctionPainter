package rainbow.function.mathfunction.simple

import rainbow.coordinate.function.mathfunction.DoubleUnaryOperator
import rainbow.function.mathfunction.MathFunction
import java.util.*

/**
 * 简化版的MathFunction，对于任意一个维度进行线性处理。默认为第一个。
 * @author Rainbow Yang
 */
abstract class SimpleMathFunction : MathFunction() {

    fun setFunction(function: DoubleUnaryOperator) = setFunction(0, listOf(function))

    fun setFunction(index: Int = 0, function: DoubleUnaryOperator) = setFunction(index, listOf(function))

    fun setFunction(index: Int = 0, vararg functions: DoubleUnaryOperator) = setFunction(index, functions.asList())

    fun setFunction(index: Int = 0, functions: List<DoubleUnaryOperator>) {
        val newFunctions = LinkedList<DoubleUnaryOperator>()
        newFunctions.addAll(functions)
        newFunctions.add(index, SELF)

        addFunctions(newFunctions)
    }
}
