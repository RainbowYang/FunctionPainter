package rainbow.inner.math;

import rainbow.coordinate.point.PointDouble;

/**
 * 数学意义上的线
 * 提供求交点等功能
 * 表达式为a*x+b*y+c=0
 *
 * @author Rainbow Yang
 */
public class Line {
    // 表达式为a*x+b*y+c=0
    private double a;
    private double b;
    private double c;

    public static final Line X_AXIS = new Line(0, 1, 0);
    public static final Line Y_AXIS = new Line(1, 0, 0);

    public Line(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * 根据两个点生成线
     *
     * @param p1 第一个点
     * @param p2 第二个点
     */
    public Line(PointDouble p1, PointDouble p2) {
        a = p1.getY() - p2.getY();
        b = -(p1.getX() - p2.getX());
        c = (p1.getX() * p2.getY() - p1.getY() * p2.getX());
    }

    /**
     * 根据一个点和倾斜角生成线
     *
     * @param p     直线要过得点
     * @param angle 倾斜角
     */
    public Line(PointDouble p, double angle) {
        if ((angle - Math.PI / 2) % Math.PI == 0) {
            a = 1;
            b = 0;
            c = -p.getX();
        } else {
            a = Math.tan(angle);
            b = -1;
            c = p.getY() - p.getY() * a;
        }

    }

    public PointDouble getCross(Line l) {
        //平行
        if (a * l.b - b * l.a == 0) {
            throw new NoCrossException(this + "has no cross with" + l);
        }
        return new PointDouble(-(c * l.b - b * l.c) / (a * l.b - b * l.a), -(a * l.c - c * l.a) / (a * l.b - b * l.a));
    }

    class NoCrossException extends RuntimeException {
        public NoCrossException(String message) {
            super(message);
        }
    }

    @Override
    public String toString() {
        return "Line{" + a + "*x+"
                + b + "*y+"
                + c + "=0" +
                '}';
    }
}
