package rainbow.inner.function.pointfunction;

import rainbow.inner.coordinate.point.PointPolar2D;
import rainbow.inner.function.PointFunction;

/**
 * 正多边形
 *
 * @author Rainbow Yang
 */
public class RegularPolygon extends PointFunction {
    //边数
    private int sides;
    //半径
    private double r = 5;
    //取点间隔
    private int step = 1;
    //第一个点的方向
    private double startAngle = Math.PI / 2;

    public RegularPolygon(int sides) {
        this.sides = sides;
    }

    public RegularPolygon(int sides, double r) {
        this.sides = sides;
        this.r = r;
    }

    public RegularPolygon(int sides, double r, int step) {
        this.sides = sides;
        this.r = r;
        this.step = step;
    }

    public RegularPolygon(int sides, double r, int step, double startAngle) {
        this.sides = sides;
        this.r = r;
        this.step = step;
        this.startAngle = startAngle;
    }

    public void calcPoint() {
        clear();
        boolean[] has = new boolean[sides];
        for (int s = 0; s < sides; s++) {
            if (has[s]) continue;

            newPoints();
            addPoint(get(s));
            has[s] = true;

            int i = (s + step) % sides;
            while (true) {
                addPoint(get(i));
                has[i] = true;
                i += step;
                i %= sides;
                if (i == s) {
                    break;
                }
            }

            addPoint(get(s));
        }
    }

    //获取第i个点
    private PointPolar2D get(int i) {
        i = i % sides;
        double stepAngle = Math.PI * 2 / sides;
        return new PointPolar2D(r, startAngle + i * stepAngle);
    }

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
        calcPoint();
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
        calcPoint();
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
        calcPoint();
    }

    public double getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(double startAngle) {
        this.startAngle = startAngle;
        calcPoint();
    }

    @Override
    public String toString() {
        return "RegularPolygon{" +
                "sides=" + sides +
                ", r=" + r +
                ", step=" + step +
                ", startAngle=" + startAngle +
                ", points=" + points +
                '}';
    }
}
