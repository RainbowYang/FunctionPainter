package rainbow.inner.coordinate.system;

import rainbow.inner.exception.FailedSetException;

/**
 * @author Rainbow Yang
 * @date 2017/4/4
 */
public class CoordinateSystemAxes2D extends CoordinateSystem {
    //x轴和y轴的角度(与三点钟方向逆时针的方向)
    private double xAngle, yAngle;

    public CoordinateSystemAxes2D() {
        init();
    }

    public void init() {
        xAngle = 0;
        yAngle = Math.PI / 2;
    }

    @Override
    public void setChanged() {
        initValues();
    }

    @Override
    protected void initValues() {
        // values = new Values();
    }

    @Override
    public String toString() {
        return "CoordinateSystemAxes2D{" +
                "xAngle=" + xAngle +
                ", yAngle=" + yAngle +
                '}';
    }

    public double getxAngle() {
        return xAngle;
    }

    /**
     * 如果设置的角度与另一轴相同，则抛出FailedSetException
     *
     * @param xAngle 需要设置的x轴的角度
     * @see FailedSetException
     */
    public void setxAngle(double xAngle) {
        if (xAngle == yAngle) {
            throw new FailedSetException("The set of xAnger was failed,because the value of xAngle" +
                    xAngle + "is equals to the value of yAngle");
        } else {
            this.xAngle = xAngle;
        }
    }

    public double getyAngle() {
        return yAngle;
    }

    /**
     * 如果设置的角度与另一轴相同，则抛出FailedSetException
     *
     * @param yAngle 需要设置的y轴的角度
     * @see FailedSetException
     */
    public void setyAngle(double yAngle) {
        if (xAngle == yAngle) {
            throw new FailedSetException("The set of yAnger was failed,because the value of yAngle" +
                    yAngle + "is equals to the value of xAngle");
        } else {
            this.yAngle = yAngle;
        }
    }
}
