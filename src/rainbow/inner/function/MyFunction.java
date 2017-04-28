package rainbow.inner.function;

import rainbow.inner.coordinate.system.CoordinateSystem;

import java.awt.*;

/**
 * @author Rainbow Yang
 */
public abstract class MyFunction {
    public static Class<? extends CoordinateSystem> clazz;

    public MyFunction(Class clazz) {
        this.clazz = clazz;
    }

    public MyFunction() {

    }

    public abstract void paintBefore(Graphics g);

    public abstract void paintAfter(Graphics g);
}

