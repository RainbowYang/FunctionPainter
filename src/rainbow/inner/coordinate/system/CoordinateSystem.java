package rainbow.inner.coordinate.system;

import rainbow.inner.coordinate.point.MyPoint;
import rainbow.inner.coordinate.point.PointDouble;


/**
 * @author Rainbow Yang
 */
public abstract class CoordinateSystem {
    protected LocationChanger changer;
    protected double x, y;

    public CoordinateSystem() {
        initLocationChanger();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public LocationChanger getLocationChanger() {
        return changer;
    }

    protected abstract void initLocationChanger();

    /**
     * 用于坐标的换算
     * 屏幕上的位置 和 在坐标中的位置
     *
     * @author Rainbow Yang
     */
    public abstract static class LocationChanger {
        private CoordinateSystem system;

        public LocationChanger(CoordinateSystem system) {
            this.system = system;
        }

        /**
         * 把坐标点转换为屏幕上的位置
         *
         * @param ps 坐标点
         * @return 屏幕上的位置
         */
        public abstract PointDouble[] toReal(MyPoint<? extends MyPoint>... ps);

        /**
         * 把坐标点转换为屏幕上的位置
         *
         * @param p 坐标点
         * @return 屏幕上的位置
         */
        public abstract PointDouble toReal(MyPoint p);

        /**
         * 把坐标点转换为屏幕上的位置
         *
         * @param ps 坐标点
         * @return 屏幕上的位置
         */
        public MyPoint<? extends MyPoint>[] toSystem(PointDouble... ps) {
            throw new UnsupportedOperationException("The toSystem has not supported by" + system.getClass().getSimpleName());
        }

        public MyPoint<? extends MyPoint> toSystem(PointDouble p) {
            throw new UnsupportedOperationException("The toSystem has not supported by" + system.getClass().getSimpleName());
        }
    }
}
