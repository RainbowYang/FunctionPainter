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

    /**
     * 默认使用二维轴坐标系
     */
    public MySystem() {
        this.coordinateSystem = new CoordinateSystemAxes2D();
    }

    public MySystem(CoordinateSystem coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
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
}
