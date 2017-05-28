package rainbow;

import rainbow.inner.coordinate.system.CoordinateSystemForAxes;
import rainbow.inner.function.MyFunction;
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
        MySystem.createSystem(new CoordinateSystemForAxes(4));

        Functions functions = MySystem.getSystem().getFunctions();
        functions.getFunctions().forEach(MyFunction::calc);
        new MainFrame();
    }

    static {
        new CodeReader(".//src//rainbow").print(CodeReader.DETAILED);
    }
}
