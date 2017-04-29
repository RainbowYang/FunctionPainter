package rainbow.outer.painter;

import rainbow.inner.system.MySystem;
import rainbow.outer.painter.tool.MyGraphics;

import java.awt.*;

/**
 * 画函数
 *
 * @author Rainbow Yang
 */
public class FunctionPainter implements MyPainter {
    @Override
    public void paint(Graphics g) {
        MyGraphics mg = new MyGraphics(g);
        MySystem.getSystem().getFunctions().getFunctions().forEach(f -> {
            f.paintBefore(mg);
            f.paintMain(mg);
            f.paintAfter(mg);
        });
    }
}
