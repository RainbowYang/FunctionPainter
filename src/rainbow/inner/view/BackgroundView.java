package rainbow.inner.view;

import rainbow.inner.system.MySystem;

import java.awt.*;

/**
 * @author Rainbow Yang
 */
public class BackgroundView extends View {
    private Color color = Color.white;

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, (int) MySystem.getSystem().getSize().getWidth(), (int) MySystem.getSystem().getSize().getHeight());
    }
}
