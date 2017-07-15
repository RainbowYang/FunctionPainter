package rainbow.coordinate.function.mathfunction.simple._2D.trigonometric_function;

import rainbow.coordinate.function.mathfunction.simple.SimpleMathFunction;

/**
 * 三角函数
 * f(x)=a*sin(wx+f)
 *
 * @author Rainbow Yang
 */
public class TrigonometricFunction extends SimpleMathFunction {
    public static final String MODE_SIN = "Sin";
    public static final String MODE_COS = "Cos";
    public static final String MODE_TAN = "Tan";
    public static final String MODE_COT = "Cot";
    public static final String MODE_SEC = "Sec";
    public static final String MODE_CSC = "Csc";

    private double a;
    private double w;
    private double f;
    private String mode;

    public TrigonometricFunction(double a, double w, double f, String mode) {
        super();
        this.a = a;
        this.w = w;
        this.f = f;
        this.mode = mode;

        switch (mode) {
            case MODE_SIN:
                setFunction(x -> a * Math.sin(w * x + f));
                break;
            case MODE_COS:
                setFunction(x -> a * Math.cos(w * x + f));
                break;
            case MODE_TAN:
                setFunction(x -> a * Math.tan(w * x + f));
                break;
            case MODE_COT:
                setFunction(x -> a / Math.tan(w * x + f));
                break;
            case MODE_SEC:
                setFunction(x -> a / Math.cos(w * x + f));
                break;
            case MODE_CSC:
                setFunction(x -> a / Math.sin(w * x + f));
                break;
        }
    }

    @Override
    public String toString() {
        return "TrigonometricFunction{f(x)=" + a + "*" + mode + "(" + w + "x+" + f + ")";
    }
}
