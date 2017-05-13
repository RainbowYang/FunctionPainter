package rainbow.inner.system;

import rainbow.inner.coordinate.system.CoordinateSystem;
import rainbow.inner.listener.Listeners;
import rainbow.inner.scalable.ComponentScalable;
import rainbow.inner.system.comp.Functions;

import java.util.ArrayList;

/**
 * 本类用于管理本系统中的大部分变量。
 *
 * @author Rainbow Yang
 */
public class MySystem extends ComponentScalable<SystemComponent> {
    public static final String name = "FunctionPainter";
    public static final String version = "V2.0";
    public static final String author = "Rainbow Yang";

    public static final double DEFAULT_WIDTH = 1300, DEFAULT_HEIGHT = 800;

    private static ArrayList<MySystem> systems = new ArrayList<>();
    private static MySystem system = new MySystem();

    private static double width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;

    private MySystem() {
    }

    public static void createSystem(CoordinateSystem coordinateSystem) {
        systems.add(system = new MySystem());
        system.setComp(coordinateSystem, new Functions(), new Listeners());
    }

    public static MySystem getSystem() {
        return system;
    }

    public static void setSystem(int index) {
        system = systems.get(index);
    }

    public Functions getFunctions() {
        return (Functions) getComp(Functions.staticGetKeyName());
    }

    public CoordinateSystem getCoordinateSystem() {
        return (CoordinateSystem) getComp(CoordinateSystem.staticGetKeyName());
    }

    public Listeners getListeners() {
        return (Listeners) getComp(Listeners.staticGetKeyName());
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
