package rainbow.inner.coordinate.system;

import rainbow.inner.point.MyPoint;

import java.awt.geom.Point2D;

/**
 * @author Rainbow Yang
 * @date 2017/4/4
 */
public class CoordinateSystemAxes2D implements CoordinateSystem {

    private double x, y;
    private double xAngle, yAngle;

    @Override
    public LocationChanger getLocationChanger() {
        return new LocationChanger() {
            @Override
            public <P extends MyPoint> P toSystem(Point2D.Double p) {
                return null;
            }

            @Override
            public <P extends MyPoint> Point2D.Double toReal(P p) {
                return null;
            }
        };
    }
}
