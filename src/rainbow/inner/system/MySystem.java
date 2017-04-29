package rainbow.inner.system;

import rainbow.inner.coordinate.system.CoordinateSystem;
import rainbow.inner.coordinate.system.CoordinateSystemForAxes;
import rainbow.inner.system.comp.Functions;

import java.util.ArrayList;

/**
 * 本类用于管理本系统中的大部分变量。
 * 单例模式。
 *
 * @author Rainbow Yang
 */
public class MySystem {

    public static final double DEFAULT_WIDTH = 1300, DEFAULT_HEIGHT = 500;

    private static ArrayList<MySystem> systems = new ArrayList<>();
    private static MySystem system = new MySystem();

    private double width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;

    private CoordinateSystem cs;
    private Functions fs;

    private MySystem() {
    }

    public void init() {
        setCoordinateSystem();
        fs = new Functions();
    }

    public static void createSystem() {
        systems.add(system = new MySystem());
        system.init();
    }

    public static MySystem getSystem() {
        return system;
    }

    public Functions getFunctions() {
        return fs;
    }

    public CoordinateSystem getCoordinateSystem() {
        return cs;
    }

    /**
     * 默认设置为平面轴坐标系
     */
    public void setCoordinateSystem() {
        this.cs = new CoordinateSystemForAxes(2);
    }

    public void setCoordinateSystem(CoordinateSystem coordinateSystem) {
        this.cs = coordinateSystem;
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

}
