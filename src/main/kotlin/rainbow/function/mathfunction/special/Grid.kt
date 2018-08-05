package rainbow.function.mathfunction.special

import rainbow.function.mathfunction.MathFunction

/**
 * 我实在不知道这个要叫什么好了 0.0.
 *
 * 通过一个 f(a,b,c..) 的多元函数
 * 如果多元函数有n个参数
 * 那么就在前n个维度上构建网格（并不就是二维），三维的类似于脚手夹

 * @author Rainbow Yang
 */
open class Grid(function: (Double, Double) -> Double) : MathFunction() {
    init {
        setRange(-10.0, 10.0, 0.1)

        for (j in start.toInt()..end.toInt()) {
            setFunctions(SELF, { j.toDouble() }, { function(it, j.toDouble()) })
            addFunctions({ j.toDouble() }, SELF, { function(j.toDouble(), it) })
        }
    }
}
