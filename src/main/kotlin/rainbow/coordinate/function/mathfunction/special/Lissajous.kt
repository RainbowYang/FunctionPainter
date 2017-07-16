package rainbow.coordinate.function.mathfunction.special

import rainbow.coordinate.function.mathfunction.DoubleUnaryOperator
import rainbow.coordinate.function.mathfunction.MathFunction

/**
 * Lissajous是任意纬度的沿着互相垂直方向的正弦振动的合成的轨迹。
 * @author Rainbow Yang
 */
class Lissajous(vararg values: Int) : MathFunction() {
    var length = 10.0

    init {
        setRange(-Math.PI, Math.PI, 0.001)

        setFunctions(MutableList<DoubleUnaryOperator>(values.size) {
            index ->
            { length * Math.sin(it * values[index]) }
        })
    }
}
