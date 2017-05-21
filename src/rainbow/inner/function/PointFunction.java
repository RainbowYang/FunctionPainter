package rainbow.inner.function;

import rainbow.inner.coordinate.point.MyPoint;
import rainbow.outer.painter.tool.MyGraphics;

import java.util.ArrayList;
import java.util.List;

/**
 * 重写paintMain(),对点进行连线
 *
 * @author Rainbow Yang
 */
public abstract class PointFunction extends MyFunction {
    //所有点集的集合
    //当图像并不是一笔完成，则需要放在不同的List中。
    protected List<List<MyPoint>> points = new ArrayList<>();
    //表示当前操作的点集的索引
    private int index = -1;

    protected PointFunction() {
        super();
    }

    @Override
    public void paintMain(MyGraphics mg) {
        points.forEach(mg::paintPoints);
    }

    @Override
    public void calc() {
        calcPoint();
    }

    public abstract void calcPoint();

    protected void newPoints() {
        points.add(new ArrayList<>());
        index++;
    }

    public void clear() {
        points.clear();
    }

    public void addPoint(MyPoint p) {
        points.get(index).add(p);
    }

    public List<List<MyPoint>> getPoints() {
        return points;
    }
}
