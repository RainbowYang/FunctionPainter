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
    public static final String name = "FunctionPrinter";
    public static final String version = "V2.0";
    public static final String author = "Rainbow Yang";

    public static final double DEFAULT_WIDTH = 1300, DEFAULT_HEIGHT = 800;

    private static ArrayList<MySystem> systems = new ArrayList<>();
    private static MySystem system = new MySystem();

    private static double width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;

    private CoordinateSystem cs;
    private Functions fs;

    private MySystem() {
    }

    public static void createSystem() {
        system.init();
    }

    public static void createSystem(CoordinateSystem coordinateSystem) {
        system.init(coordinateSystem);
    }

    public void init() {
        systems.add(system = new MySystem());
        setCoordinateSystem();
        fs = new Functions();
    }

    public void init(CoordinateSystem coordinateSystem) {
        systems.add(system = new MySystem());
        setCoordinateSystem(coordinateSystem);
        fs = new Functions();
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

    public static double getWidth() {
        return width;
    }

    public static void setWidth(double newWidth) {
        width = newWidth;
    }

    public static double getHeight() {
        return height;
    }

    public static void setHeight(double newHeight) {
        height = newHeight;
    }

}
