package rainbow.inner.coordinate.system.comp.unused;

import rainbow.inner.coordinate.point.MyPoint;
import rainbow.inner.coordinate.point.PointDouble;
import rainbow.inner.coordinate.system.CoordinateSystem;

/**
 * 包装了一些坐标系移动的方法
 *
 * @author Rainbow Yang
 */
public class Mover implements CoordinateSystemComponent {
    private CoordinateSystem cs;

    public Mover(CoordinateSystem cs) {
        this.cs = cs;
    }

    /**
     * 在CoordinateSystem的x的基础上加上dx
     *
     * @param dx 添加的横坐标
     * @return this 以用于链式调用
     */
    public Mover moveX(double dx) {
        cs.setX(cs.getX() + dx);
        return this;
    }

    /**
     * 在CoordinateSystem的y的基础上加上dy
     *
     * @param dy 添加的纵坐标
     * @return this 以用于链式调用
     */
    public Mover moveY(double dy) {
        cs.setY(cs.getY() + dy);
        return this;
    }

    /**
     * 在CoordinateSystem的x的基础上加上dx
     * 在CoordinateSystem的y的基础上加上dy
     *
     * @param dx 添加的横坐标
     * @param dy 添加的纵坐标
     * @return this 以用于链式调用
     */
    public Mover move(double dx, double dy) {
        moveX(dx);
        moveY(dy);
        return this;
    }

    /**
     * 相当于调用了
     * cs.setX(x);
     * cs.setY(y);
     *
     * @param x 要设置的横坐标
     * @param y 要设置的纵坐标
     * @return this 以用于链式调用
     */
    public Mover moveTo(double x, double y) {
        cs.setX(x);
        cs.setY(y);
        return this;
    }

    /**
     * 把原点移到pd的位置
     *
     * @param pd 要设置的点
     * @return this 以用于链式调用
     */
    public Mover moveTo(PointDouble pd) {
        return moveTo(pd.getX(), pd.getY());
    }

    public Mover moveTo(MyPoint p) {
        return moveTo(cs.getLocationChanger().toReal(p));
    }

    public Mover moveToOpposite(MyPoint p) {
        return moveTo(cs.getLocationChanger().toReal(p.times(-1)));
    }

    @Override
    public String getKeyName() {
        return staticGetKeyName();
    }

    public static String staticGetKeyName() {
        return "Mover";
    }
}
