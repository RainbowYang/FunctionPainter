package rainbow;

import rainbow.inner.coordinate.system.CoordinateSystemForAxes;
import rainbow.inner.function.PointFunction;
import rainbow.inner.function.pointfunction.RegularPolygon;
import rainbow.inner.system.MySystem;
import rainbow.inner.system.comp.Functions;
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
        functionTest();
        new MainFrame();
    }

    public static void functionTest() {
        MySystem.createSystem(new CoordinateSystemForAxes(3));
        // MySystem.getSystem().getFunctions().add(new LogFunction(1, Math.E));
        // MySystem.getSystem().getFunctions().add(new PowerFunction("1*x^4+2*x^3"));
        Functions functions = MySystem.getSystem().getFunctions();
        // functions.add(new ConicSection(5, 10, ConicSection.MODE_HYPERBOLA_X));
        functions.add(new RegularPolygon(100, 100, 24));
        functions.getFunctions().forEach(f -> {
            ((PointFunction) f).calcPoint();
            System.out.println(f);
        });

        // System.out.println(Arrays.toString(MySystem.getSystem().getCoordinateSystem().
        //         getLocationChanger().toReal(mf.getPoints().get(0))));
        // System.out.println(mf);
    }

    static {
        new CodeReader(".//src//rainbow").print(CodeReader.DETAILED);
    }
}
