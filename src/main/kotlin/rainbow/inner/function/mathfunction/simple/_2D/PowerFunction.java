package rainbow.inner.function.mathfunction.simple._2D;

import rainbow.inner.function.mathfunction.simple.SimpleMathFunction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;

/**
 * 幂指数函数
 * 表达式: f(x)=a*x^n+b*x^m+...
 *
 * @author Rainbow Yang
 */
public class PowerFunction extends SimpleMathFunction {
    //K=指数，V=值
    private Map<Double, Double> map;

    public PowerFunction(Map<Double, Double> map) {
        this.map = map;
        setFunction(toFunction(map));
    }

    public PowerFunction(String strFun) {
        this.map = toMap(strFun);
        setFunction(toFunction(map));
    }

    public static Map<Double, Double> toMap(String strFun) {
        HashMap<Double, Double> partMap = new HashMap<>();
        Arrays.asList(strFun.split("[+]")).forEach(p -> {
            if (p.contains("*x^")) {
                String[] part = p.split("[*x^]");
                partMap.put(new Double(part[3]), new Double(part[0]));
            } else {
                partMap.put(new Double(0), new Double(p));
            }
        });
        return partMap;
    }

    public static DoubleUnaryOperator toFunction(Map<Double, Double> map) {
        // return x -> map.keySet().stream().reduce((sum, key) -> sum += map.get(key) * Math.pow(x, key)).get();
        return x -> {
            final double[] sum = {0};
            map.forEach((n, a) -> {
                sum[0] += a * Math.pow(x, n);
            });
            return sum[0];
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("f(x)=");
        map.forEach((n, a) -> {
            sb.append(a + "*x^" + n);
        });
        return "PowerFunction{" + sb.toString() + '}';
    }
}
