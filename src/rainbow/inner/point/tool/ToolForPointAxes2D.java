package rainbow.inner.point.tool;

import rainbow.inner.point.PointAxes2D;

/**
 * @author Rainbow Yang
 * @date 2017/4/2
 */
public class ToolForPointAxes2D implements ToolForMyPoint<PointAxes2D> {
    private static ToolForPointAxes2D tool = new ToolForPointAxes2D();

    public static ToolForPointAxes2D getTool() {
        return tool;
    }

    @Override
    public PointAxes2D add(PointAxes2D... pointAxes2DS) {
        return null;
    }

    @Override
    public PointAxes2D spin(PointAxes2D... pointAxes2DS) {
        return null;
    }
}
