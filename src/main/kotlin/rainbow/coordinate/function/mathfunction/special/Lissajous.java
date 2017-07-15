package rainbow.coordinate.function.mathfunction.special;

import rainbow.coordinate.function.mathfunction.MathFunction;

import java.util.ArrayList;
import java.util.function.DoubleUnaryOperator;

/**
 * Wiki
 * 数学上，利萨茹（Lissajous）曲线（又称利萨茹图形、李萨如图形或鲍迪奇（Bowditch）曲线）是两个沿着互相垂直方向的正弦振动的合成的轨迹。
 * <p>
 * 但本类可以生成任意纬度的利萨茹曲线。
 *
 * @author Rainbow Yang
 */
public class Lissajous extends MathFunction {
    public double length = 10;

    public Lissajous(int... values) {
        setRange(-Math.PI, Math.PI, 0.001);

        ArrayList<DoubleUnaryOperator> functions = new ArrayList<>();
        for (int value : values) {
            functions.add(i -> length * Math.sin(value * i));
        }
        setFunctions(functions);
    }
}
