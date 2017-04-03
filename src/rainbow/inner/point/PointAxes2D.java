package rainbow.inner.point;

import rainbow.inner.point.tool.ToolForMyPoint;
import rainbow.inner.point.tool.ToolForPointAxes2D;

/**
 * @author Rainbow Yang
 * @date 2017/4/2
 */
public class PointAxes2D implements MyPoint {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public ToolForMyPoint<PointAxes2D> getTool() {
        return ToolForPointAxes2D.getTool();
    }
}
