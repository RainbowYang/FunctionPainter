package rainbow.inner.coordinate.system.comp.unused;

import rainbow.inner.coordinate.system.CoordinateSystem;
import rainbow.inner.painter.graphics.MyGraphics;

/**
 * 用于绘制坐标系
 *
 * @author Rainbow Yang
 */
public abstract class SystemPainter implements CoordinateSystemComponent {
    private CoordinateSystem cs;

    public SystemPainter(CoordinateSystem cs) {
        this.cs = cs;
    }

    /**
     * 画数字
     *
     * @param mg 画笔
     */
    public void paintNum(MyGraphics mg) {
    }

    /**
     * 画原点
     *
     * @param mg 画笔
     */

    public void paintOrigin(MyGraphics mg) {
    }

    /**
     * 画坐标轴
     *
     * @param mg 画笔
     */
    public void paintAxes(MyGraphics mg) {
    }

    /**
     * 画网格
     *
     * @param mg 画笔
     */
    public void paintGrid(MyGraphics mg) {
    }

    public void paintFunctions(MyGraphics mg) {
        cs.getFunctions().getFunctions().forEach(f -> {
            f.paintBefore(mg);
            f.paintMain(mg);
            f.paintAfter(mg);
        });
    }

    @Override
    public String getKeyName() {
        return staticGetKeyName();
    }

    public static String staticGetKeyName() {
        return "SystemPainter";
    }
}
