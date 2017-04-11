package rainbow.inner.coordinate.point;

/**
 * @author Rainbow Yang
 * @date 2017/4/11
 */
public class PointDouble {
    private double x;
    private double y;

    public PointDouble(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
