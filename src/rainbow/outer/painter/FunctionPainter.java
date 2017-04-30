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
    private Color color = Color.black;

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        MyGraphics mg = new MyGraphics(g);
        MySystem.getSystem().getFunctions().getFunctions().forEach(f -> {
            f.paintBefore(mg);
            f.paintMain(mg);
            f.paintAfter(mg);
        });
    }
}
