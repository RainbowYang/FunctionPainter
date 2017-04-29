package rainbow.outer.painter.tool;

import java.awt.*;

/**
 * 包装Graphics，在内部自动进行坐标转换
 *
 * @author Rainbow Yang
 * @see rainbow.inner.coordinate.system.CoordinateSystem
 */
public class MyGraphics {
    private Graphics g;

    public MyGraphics(Graphics g) {
        this.g = g;
    }

    public Graphics getGraphics() {
        return g;
    }
    //todo 添加功能
}
