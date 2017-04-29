package rainbow.outer.painter;

import rainbow.inner.system.MySystem;

import java.awt.*;

/**
 * 画背景
 *
 * @author Rainbow Yang
 */
public class BackPainter implements MyPainter {
    private Color color = Color.GRAY;

    @Override
    public void paint(Graphics g) {
        g.fillRect(0, 0, (int) MySystem.getSystem().getWidth(), (int) MySystem.getSystem().getHeight());
    }
}
