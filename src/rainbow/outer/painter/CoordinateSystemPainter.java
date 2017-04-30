package rainbow.outer.painter;

import rainbow.inner.coordinate.point.PointForAxes;
import rainbow.outer.painter.tool.MyGraphics;

import java.awt.*;

/**
 * @author Rainbow Yang
 */
public class CoordinateSystemPainter implements MyPainter {
    @Override
    public void paint(Graphics g) {
        //todo MyGraphics Test
        MyGraphics mg = new MyGraphics(g);
        g.setColor(Color.BLACK);
        mg.paintLine(new PointForAxes(-1, -1), new PointForAxes(1, 1),MyGraphics.MODE_STRAIGHT_LINE);
    }
}
