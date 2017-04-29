package rainbow.outer.painter;

import rainbow.inner.system.MySystem;

import java.awt.*;

/**
 * 画背景
 *
 * @author Rainbow Yang
 */
public class BackPainter implements MyPainter {
    private Color color = Color.white;

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, (int) MySystem.getSystem().getWidth(), (int) MySystem.getSystem().getHeight());
    }
}
