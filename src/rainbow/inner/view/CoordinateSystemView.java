package rainbow.inner.view;

import rainbow.inner.coordinate.system.comp.SystemPainter;
import rainbow.inner.system.MySystem;
import rainbow.inner.view.graphics.MyGraphics;

import java.awt.*;

/**
 * @author Rainbow Yang
 */
public class CoordinateSystemView extends View {
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
        painter.paintFunctions(mg);
    }
}
