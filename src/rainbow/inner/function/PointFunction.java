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
    protected List<List<MyPoint>> pointsList = new ArrayList<>();
    //表示当前操作的点集的索引
    private int index = -1;

    public PointFunction() {
    }

    @Override
    public void paintMain(MyGraphics mg) {
        pointsList.forEach(mg::paintPoints);
    }

    /**
     * 用于子类进行点的计算
     */
    @Override
    public void calc() {
        calcPoint();
    }

    public abstract void calcPoint();

    /**
     * 产生一个新的点集
     */
    protected void newPoints() {
        pointsList.add(new ArrayList<>());
        index++;
    }

    /**
     * 清空pointsList
     * 注意：此时无法调用addPoint(MyPoint p)，要先调用newPoints()来产生新的点集
     */
    public void clear() {
        pointsList.clear();
    }

    /**
     * 向最后一个点集中添加点
     *
     * @param p 需要添加的点
     */
    public void addPoint(MyPoint p) {
        pointsList.get(index).add(p);
    }

    public List<List<MyPoint>> getPointsList() {
        return pointsList;
    }
}
