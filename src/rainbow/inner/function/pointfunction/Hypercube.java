package rainbow.inner.function.pointfunction;

import rainbow.inner.coordinate.point.PointForAxes;
import rainbow.inner.function.MyFunction;
import rainbow.inner.painter.graphics.MathGraphics;

/**
 * 任意维度的超方体
 *
 * @author Rainbow Yang
 */
public class Hypercube extends MyFunction {
    private double length;
    private int size;

    public Hypercube(double length, int size) {
        this.length = length;
        this.size = size;
    }

    @Override
    public void paintMain(MathGraphics mg) {
        mg.paintLocation(new PointForAxes(length, size, true));
    }
}
