package rainbow.inner.function;

import rainbow.outer.painter.tool.MyGraphics;

/**
 * 函数顶类
 *
 * @author Rainbow Yang
 */
public interface MyFunction {
    default void paintBefore(MyGraphics mg) {
    }

    default void paintMain(MyGraphics mg) {
    }

    default void paintAfter(MyGraphics mg) {
    }
}

