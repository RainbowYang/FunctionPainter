package rainbow;

import rainbow.inner.coordinate.system.CoordinateSystemForAxes;
import rainbow.inner.coordinate.system.comp.Functions;
import rainbow.inner.function.mathfunction.special.Net;
import rainbow.inner.system.MySystem;
import rainbow.outer.frame.MainFrame;
import rainbow.tools.CodeReader;

/**
 * 这是FunctionPainter2，一个重新开始的版本
 * 没错，之前的版本最终被我放弃了
 *
 * @author Rainbow Yang
 */
public class Start {
    public static void main(String[] args) {
        MySystem.getSystem().setCoordinateSystem(new CoordinateSystemForAxes(4));

        Functions functions = MySystem.getSystem().getCoordinateSystem().getFunctions();

        // functions.add(new ExpFunction(1, Math.E));
        // functions.add(new LogFunction(1, Math.E));
        // functions.add(new PowerFunction("1*x^2"));
        //
        // functions.add(new TrigonometricFunction(1, 1, 0, MODE_SIN));
        // functions.add(new TrigonometricFunction(1, 1, 0, MODE_COS));
        // functions.add(new TrigonometricFunction(1, 1, 0, MODE_TAN));
        // functions.add(new TrigonometricFunction(1, 1, 0, MODE_COT));
        // functions.add(new TrigonometricFunction(1, 1, 0, MODE_SEC));
        // functions.add(new TrigonometricFunction(1, 1, 0, MODE_CSC));
        //
        // functions.add(new ConicSection(5, 5, MODE_ELLIPSE_X));
        // functions.add(new ConicSection(5, 5, MODE_ELLIPSE_Y));
        // functions.add(new ConicSection(5, 5, MODE_HYPERBOLA_X));
        // functions.add(new ConicSection(5, 5, MODE_HYPERBOLA_Y));
        // functions.add(new ConicSection(5, MODE_PARABOLA_X));
        // functions.add(new ConicSection(5, MODE_PARABOLA_Y));
        //
        // functions.add(new Ellipsoid(6, 6, 6));
        // functions.add(new Lissajous(13, 18, 27));
        // functions.add(new RegularPolygon(6, 5, 2));
        //
        // functions.add(new FermatSpiral(100, 1));
        // functions.add(new IsometricSpiral(1, 0.5));
        // functions.add(new HyperbolicSpiral(1));
        // functions.add(new ArchimedeanSpiral(1));
        // functions.add(new LituusSpiral(1));
        //
        // functions.add(new Cycloid());
        // functions.add(new Hypotrochoid(10, 4, 1));
        // functions.add(new Hypocycloid(10, 4));
        // functions.add(new Epitrochoid(6, 4, 4));
        // functions.add(new Epicycloid(10, 3));

        functions.add(new Net((x, y, z) -> -10 / Math.sqrt(0.000001 + (x * x) + (y * y))));

        functions.clacFunctions();

        new MainFrame();
    }

    static {
        new CodeReader(".//src//rainbow").print(CodeReader.DETAILED);
    }
}
