package rainbow.outer.painter;

import rainbow.inner.coordinate.system.comp.SystemPainter;
import rainbow.inner.system.MySystem;
import rainbow.outer.painter.tool.MyGraphics;

import java.awt.*;

/**
 * @author Rainbow Yang
 */
public class CoordinateSystemPainter implements MyPainter {
    private Color color = Color.black;

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        MyGraphics mg = new MyGraphics(g);
        SystemPainter painter = MySystem.getSystem().getCoordinateSystem().getPainter();
        painter.paintOrigin(mg);
        painter.paintGrid(mg);
        painter.paintAxes(mg);
        painter.paintNum(mg);

    }
}
