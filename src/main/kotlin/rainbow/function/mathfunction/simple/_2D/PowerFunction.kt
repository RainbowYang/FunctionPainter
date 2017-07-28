package rainbow.function.mathfunction.simple._2D

import rainbow.function.mathfunction.simple.SimpleMathFunction
import java.util.*
import java.util.stream.Collectors.toMap

/**
 * 幂指数函数
 * 表达式: f(x)=a*x^n+b*x^m+...

 * @author Rainbow Yang
 */
class PowerFunction(var map: MutableMap<Double, Double>) /* K=指数，V=值 */ : SimpleMathFunction() {

    constructor(stringExpression: String) : this(toMap(stringExpression))

    companion object {
        fun toMap(strFun: String): MutableMap<Double, Double> {
            val partMap = HashMap<Double, Double>()
            Arrays.asList(*strFun.split("[+]".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()).forEach { p ->
                if (p.contains("*x^")) {
                    val part = p.split("[*x^]".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    partMap.put(part[3].toDouble(), part[0].toDouble())
                } else {
                    partMap.put(0.0, p.toDouble())
                }
            }
            return partMap
        }
    }

    //
//
//    fun toFunction(map: Map<Double, Double>): DoubleUnaryOperator {
//        // return x -> map.keySet().stream().reduce((sum, key) -> sum += map.get(key) * Math.pow(x, key)).get();
//        return { x ->
//            val sum = doubleArrayOf(0.0)
//            map.forEach { n, a -> sum[0] += a * Math.pow(x, n) }
//            sum[0]
//        }
//    }
//
    override fun toString(): String {
        val sb = StringBuilder("f(x)=")
        map.forEach { n, a -> sb.append(a.toString() + "*x^" + n) }
        return "PowerFunction{" + sb.toString() + '}'
    }
}
