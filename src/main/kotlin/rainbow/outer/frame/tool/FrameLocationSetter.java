package rainbow.outer.frame.tool;

import java.awt.*;

/**
 * @author Rainbow Yang
 */
public class FrameLocationSetter {

    /**
     * 将窗体设置在屏幕的中心
     *
     * @param f 需要设置的窗体
     */
    public static void center(Frame f) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        double width = d.getWidth();
        double height = d.getHeight();
        f.setLocation((int) (width - f.getWidth()) / 2, (int) (height - f.getHeight()) / 2);
    }
}
