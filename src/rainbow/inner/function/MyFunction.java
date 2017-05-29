package rainbow.inner.function;

import rainbow.inner.view.graphics.MyGraphics;

/**
 * 所有能画在坐标系上的东西的父类
 *
 * @author Rainbow Yang
 * @see PointFunction
 */
public abstract class MyFunction {
    protected MyFunction() {
    }

    /**
     * 钩子
     * 先画的画面，相当于背景
     *
     * @param mg 包装的画笔
     */
    public void paintBefore(MyGraphics mg) {
    }

    /**
     * 钩子
     * 主要画面
     *
     * @param mg 包装的画笔
     */
    public void paintMain(MyGraphics mg) {
    }

    /**
     * 钩子
     * 后画的画面，相当于背景
     *
     * @param mg 包装的画笔
     */
    public void paintAfter(MyGraphics mg) {
    }


    /**
     * 用于初始化
     */
    public void calc() {
    }
}

