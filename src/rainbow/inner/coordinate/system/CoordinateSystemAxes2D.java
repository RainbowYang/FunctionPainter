package rainbow.inner.coordinate.system;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;
import rainbow.inner.coordinate.point.MyPoint;
import rainbow.inner.exception.FailedSetException;

import java.awt.geom.Point2D;

/**
 * @author Rainbow Yang
 * @date 2017/4/4
 */
public class CoordinateSystemAxes2D extends CoordinateSystem {
    //原点的坐标
    private double x, y;
    //x轴和y轴的角度
    private double xAngle, yAngle;

    @Override
    public void setChanged() {

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

    public double getxAngle() {
        return xAngle;
    }

    public void setxAngle(double xAngle) {
        if (xAngle == yAngle) {
            throw new FailedSetException("The set of xAnger was failed,because the value of xAngle" +
                    xAngle + "is equals to the value of yAngle");
        } else {
            this.xAngle = xAngle;
        }
    }

    public double getyAngle() {
        if (xAngle == yAngle) {
            throw new FailedSetException("The set of yAnger was failed,because the value of yAngle" +
                    yAngle + "is equals to the value of xAngle");
        } else {
            return yAngle;
        }
    }

    public void setyAngle(double yAngle) {
        this.yAngle = yAngle;
    }
}
