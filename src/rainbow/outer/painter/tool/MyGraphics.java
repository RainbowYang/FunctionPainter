package rainbow.outer.painter.tool;

import rainbow.inner.coordinate.point.MyPoint;
import rainbow.inner.coordinate.point.PointDouble;
import rainbow.inner.system.MySystem;

import java.awt.*;
import java.util.List;


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
    public void paintPoints(MyPoint... ps) {
    }

    public void paintPoints(List<MyPoint> ps) {
        Polygon p = new Polygon();
        for (PointDouble point : MySystem.getSystem().getCoordinateSystem().getLocationChanger().toReal(ps)) {
            p.addPoint((int) point.getX(), (int) point.getY());
        }
        g.drawPolygon(p);
    }
}
