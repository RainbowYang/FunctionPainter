package rainbow.inner.function;

import rainbow.inner.coordinate.point.MyPoint;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rainbow Yang
 * @date 2017/4/4
 */
public class MathFunction extends MyFunction {
    protected List<ArrayList<? extends MyPoint>> lists;

    public MathFunction(Class clazz) {
        super(clazz);
        this.lists = new ArrayList<>();
    }

    public List<ArrayList<? extends MyPoint>> getPoints() {
        return lists;
    }

    @Override
    public void paintBefore(Graphics g) {
    }

    @Override
    public void paintAfter(Graphics g) {
    }
}
