package rainbow.inner.function.mathfunction;

import rainbow.inner.function.MathFunction;

/**
 * 三角函数
 * f(x)=a*sin(wx+f)
 *
 * @author Rainbow Yang
 */
public class TrigonometricFunction extends MathFunction {
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
                function = x -> a * Math.sin(w * x + f);
                break;
            case MODE_COS:
                function = x -> a * Math.cos(w * x + f);
                break;
            case MODE_TAN:
                function = x -> a * Math.tan(w * x + f);
                break;
            case MODE_COT:
                function = x -> a / Math.tan(w * x + f);
                break;
            case MODE_SEC:
                function = x -> a / Math.cos(w * x + f);
                break;
            case MODE_CSC:
                function = x -> a / Math.sin(w * x + f);
                break;
        }
    }

    @Override
    public String toString() {
        return "TrigonometricFunction{f(x)=" + a + "*" + mode + "(" + w + "x+" + f + ")";
    }
}
