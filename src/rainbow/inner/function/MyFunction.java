package rainbow.inner.function;

import rainbow.outer.painter.tool.MyGraphics;

/**
 * 函数顶类
 *
 * @author Rainbow Yang
 */
public abstract class MyFunction {
    public MyFunction() {
    }

    public abstract void paintBefore(MyGraphics g);

    public abstract void paintMain(MyGraphics g);

    public abstract void paintAfter(MyGraphics g);
}

