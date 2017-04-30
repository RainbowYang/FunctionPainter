package rainbow;

import rainbow.inner.coordinate.system.CoordinateSystemForAxes;
import rainbow.inner.function.MathFunction;
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
        System.out.println("这里只是一个暂置的Start类");
        functionTest();
        new MainFrame();
    }

    public static void functionTest() {
        MySystem.createSystem(new CoordinateSystemForAxes(3));
        // ((CoordinateSystemForAxes)MySystem.getSystem().getCoordinateSystem()).setLength(2,50);
        ((CoordinateSystemForAxes) MySystem.getSystem().getCoordinateSystem()).change(1, 2);
        MySystem.getSystem().getFunctions().add(new MathFunction(x -> Math.sin(x)));
        MathFunction mf = (MathFunction) MySystem.getSystem()
                .getFunctions().getFunctions().get(0);
        mf.calcPoint();
        // System.out.println(Arrays.toString(MySystem.getSystem().getCoordinateSystem().
        //         getLocationChanger().toReal(mf.getPoints().get(0))));
        // System.out.println(mf);
    }

    static {
        new CodeReader(".//src//rainbow").print(CodeReader.DETAILED);
    }
}
