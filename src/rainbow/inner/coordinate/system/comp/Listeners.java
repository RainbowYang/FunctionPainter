package rainbow.inner.coordinate.system.comp;

import rainbow.inner.coordinate.system.CoordinateSystem;

import java.awt.event.*;

/**
 * 本类用于接收鼠标等事件并进行处理。
 *
 * @author Rainbow Yang
 */
public abstract class Listeners {
    private CoordinateSystem system;

    public Listeners(CoordinateSystem system) {
        this.system = system;
    }

    public KeyListener getKeyListener() {
        return new KeyAdapter() {
        };
    }

    public MouseAdapter getMouseAdapter() {
        return new MouseAdapter() {
        };
    }
}
