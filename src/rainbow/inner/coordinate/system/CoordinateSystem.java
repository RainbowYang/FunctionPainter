package rainbow.inner.coordinate.system;

import rainbow.inner.coordinate.system.comp.*;
import rainbow.inner.scalable.ComponentScalable;
import rainbow.inner.system.MySystem;
import rainbow.inner.system.SystemComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * 坐标系
 * 主要负责坐标的换算
 *
 * @author Rainbow Yang
 */
public abstract class CoordinateSystem extends ComponentScalable<CoordinateSystemComponent> implements SystemComponent {
    private double x, y;
    private List<Axis> axes = new ArrayList<>();

    {
        x = MySystem.getSystem().getWidth() / 2;
        y = MySystem.getSystem().getHeight() / 2;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void addAxes(int i) {
        for (int j = 0; j < i; j++) {
            axes.add(new Axis());
        }
    }

    public void setAngles(double[] angles) {
        for (int i = 0; i < angles.length; i++) {
            setAngle(i, angles[i]);
        }
    }

    public void setAngle(int index, double angle) {
        axes.get(index).setAngle(angle);
    }

    public void setLengths(double[] lengths) {
        for (int i = 0; i < lengths.length; i++) {
            setLength(i, lengths[i]);
        }
    }

    public void setLength(int index, double length) {
        axes.get(index).setLength(length);
    }

    public void timesLengths(double times) {
        axes.forEach(axis -> axis.timesLength(times));
    }

    @Override
    public String getKeyName() {
        return staticGetKeyName();
    }

    public static String staticGetKeyName() {
        return "CoordinateSystem";
    }

    public static class Axis {
        //所指角度
        private double angle;
        //单位长度
        private double length;
        public static double DEFAULT_LENGTH = 40;

        /**
         * 长度默认为40
         */
        public Axis() {
            this.angle = 0;
            this.length = 40;
        }

        /**
         * 长度默认为40
         *
         * @param angle 所指向的角度
         */
        public Axis(double angle) {
            this.angle = angle;
            this.length = DEFAULT_LENGTH;
        }

        public Axis(double angle, double length) {
            this.angle = angle;
            this.length = DEFAULT_LENGTH;
        }

        public double getAngle() {
            return angle;
        }

        public void setAngle(double angle) {
            this.angle = angle;
        }

        public void addAngle(double angle) {
            this.angle += angle;
        }

        public double getLength() {
            return length;
        }

        public void setLength(double length) {
            this.length = length;
        }

        public void addLength(double length) {
            this.length += length;
        }

        public void timesLength(double times) {
            this.length *= times;
        }
    }
}
