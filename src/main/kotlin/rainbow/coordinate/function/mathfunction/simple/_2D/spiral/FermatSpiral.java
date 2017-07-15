package rainbow.coordinate.function.mathfunction.simple._2D.spiral;

import static java.lang.Math.sqrt;

/**
 * 费马螺线
 * 表达式：r^2 =theta
 *
 * @author Rainbow Yang
 */
public class FermatSpiral extends Spiral {
    //貌似这个东西是不能变的
    {
        setFunction(i -> sqrt(i));
        setFunction(i -> -sqrt(i));

        setStart(0);
    }

    public FermatSpiral() {
    }

    public FermatSpiral(double end) {
        setEnd(end);
    }

    public FermatSpiral(double end, double step) {
        setStep(step);
        setEnd(end);
    }
}
