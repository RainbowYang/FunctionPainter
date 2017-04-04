package rainbow.inner.coordinate.system;

import rainbow.inner.point.MyPoint;

import java.awt.*;

/**
 * @author Rainbow Yang
 * @date 2017/4/4
 */
public interface CoordinateSystem {

    public LocationChanger getLocationChanger();

    interface LocationChanger {
        public <P extends MyPoint> P toSystem(Point.Double p);

        public <P extends MyPoint> Point.Double toReal(P p);
    }
}
