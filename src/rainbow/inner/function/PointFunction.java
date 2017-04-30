package rainbow.inner.function;

import rainbow.inner.coordinate.point.MyPoint;
import rainbow.outer.painter.tool.MyGraphics;

import java.util.ArrayList;
import java.util.List;

/**
 * 重写paintMain(),对List<MyPoint>自动连线
 *
 * @author Rainbow Yang
 */
public class PointFunction extends MyFunction {
    //所有点集的集合
    protected List<List<MyPoint>> points = new ArrayList<>();
    private int index = -1;

    PointFunction() {
        super();
    }

    @Override
    public void paintMain(MyGraphics mg) {
        for (List<MyPoint> ps : points) {
            mg.paintPoints(ps);
        }
    }

    public void newPoints() {
        points.add(new ArrayList<>());
        index++;
    }

    public void addPoint(MyPoint p) {
        points.get(index).add(p);
    }

    public List<List<MyPoint>> getPoints() {
        return points;
    }
}
