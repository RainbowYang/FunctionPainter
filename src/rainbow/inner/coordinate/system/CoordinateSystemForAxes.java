package rainbow.inner.coordinate.system;

import rainbow.inner.coordinate.point.MyPoint;
import rainbow.inner.coordinate.point.PointDouble;
import rainbow.inner.coordinate.point.PointForAxes;
import rainbow.inner.system.MySystem;

import java.util.Arrays;

/**
 * @author Rainbow Yang
 */
public class CoordinateSystemForAxes extends CoordinateSystem {
    //每个维度与三点钟方向沿逆时针方向的夹角
    private double[] angles;
    //每个维度的单位长度
    private double[] lengthes;

    public CoordinateSystemForAxes(int size) {
        angles = new double[size];
        lengthes = new double[size];
        init();
    }

    public CoordinateSystemForAxes(double... angles) {
        this.angles = angles;
        this.lengthes = new double[angles.length];
        init();
    }

    {
        x = MySystem.getSystem().getWidth() / 2;
        y = MySystem.getSystem().getHeight() / 2;
    }

    public void init() {
        for (int i = 0; i < size(); i++) {
            lengthes[i] = 40;
        }
        switch (angles.length) {
            case 3:
                angles[2] = Math.PI * 5 / 4;
            case 2:
                angles[1] = Math.PI / 2;
            case 1:
                angles[0] = 0;
        }
    }

    @Override
    protected void initLocationChanger() {
        changer = new CoordinateSystem.LocationChanger(this) {
            @Override
            public PointDouble[] toReal(MyPoint<? extends MyPoint>... ps) {
                return new PointDouble[0];
            }

            @Override
            public PointDouble toReal(MyPoint p) {
                PointForAxes pa = (PointForAxes) p;
                double px = x, py = y;
                for (int i = 0; i < size(); i++) {
                    px += Math.cos(angles[i]) * lengthes[i] * pa.get(i);
                    py -= Math.sin(angles[i]) * lengthes[i] * pa.get(i);
                }
                return new PointDouble(px, py);
            }
        };
    }

    public int size() {
        return angles.length;
    }

    @Override
    public String toString() {
        return "CoordinateSystemForAxes{" +
                "angles=" + Arrays.toString(angles) +
                ", lengthes=" + Arrays.toString(lengthes) +
                '}';
    }
}
