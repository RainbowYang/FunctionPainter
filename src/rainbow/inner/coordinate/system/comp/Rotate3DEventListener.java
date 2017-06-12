package rainbow.inner.coordinate.system.comp;

import rainbow.inner.coordinate.system.CoordinateSystem;
import rainbow.inner.coordinate.system.event.RotateEvent;

/**
 * @author Rainbow Yang
 */
public class Rotate3DEventListener extends EventListener {
    public Rotate3DEventListener(CoordinateSystem cs) {
        super(cs);
    }

    private double xAngle = 30;
    private double yAngle = 30;

    {
        resetAngleAndLength();
    }

    @Override
    public void rotate(RotateEvent event) {
        xAngle -= event.getDx() / 10;
        yAngle += event.getDy() / 10;

        resetAngleAndLength();
    }

    private void resetAngleAndLength() {
        if (cs.getAxes().getSize() >= 3) {
            cs.getAxes().setLengthTimes(2, Math.cos(Math.toRadians(yAngle)));
        }

        double x = -Math.sin(Math.toRadians(xAngle));
        double y = -Math.cos(Math.toRadians(xAngle)) * Math.sin(Math.toRadians(yAngle));

        cs.getAxes().setAngle(0, Math.atan2(y, x));
        cs.getAxes().setLengthTimes(0, Math.sqrt(x * x + y * y));

        x = -Math.sin(Math.toRadians(xAngle - 90));
        y = -Math.cos(Math.toRadians(xAngle - 90)) * Math.sin(Math.toRadians(yAngle));

        cs.getAxes().setAngle(1, Math.atan2(y, x));
        cs.getAxes().setLengthTimes(1, Math.sqrt(x * x + y * y));

    }
}
