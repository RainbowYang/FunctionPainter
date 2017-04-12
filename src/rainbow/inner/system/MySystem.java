package rainbow.inner.system;

import rainbow.inner.coordinate.system.CoordinateSystem;
import rainbow.inner.coordinate.system.CoordinateSystemAxes2D;

/**
 * 本类用于管理本系统中的大部分变量。
 * 单例模式。
 *
 * @author Rainbow Yang
 * @date 2017/4/11
 */
public class MySystem {

    private static MySystem mySystem = new MySystem();

    private CoordinateSystem coordinateSystem;

    public static final double DEFAULT_WIDTH = 1300, DEFAULT_HEIGHT = 500;

    private double width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;

    private double x, y;

    private double step = 0.01;


    public MySystem() {
        init();
    }

    public void init() {
        x = width / 2;
        y = height / 2;
    }

    public void setChanged() {
        coordinateSystem.setChanged();
    }

    public static MySystem getSystem() {
        return mySystem;
    }

    public CoordinateSystem getCoordinateSystem() {
        return coordinateSystem;
    }

    public void setCoordinateSystem(CoordinateSystem coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
    }

    /**
     * 默认设置为平面轴坐标系
     */
    public void setCoordinateSystem() {
        this.coordinateSystem = new CoordinateSystemAxes2D();
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }
}
