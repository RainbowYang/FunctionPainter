package rainbow.inner.coordinate.system.event;

import rainbow.inner.coordinate.system.CoordinateSystem;

import java.awt.event.MouseEvent;

/**
 * @author Rainbow Yang
 */
public class RotateEvent implements CoordinateSystemEvent {
    private MouseEvent event1;
    private MouseEvent event2;

    public RotateEvent(MouseEvent event1, MouseEvent event2) {
        this.event1 = event1;
        this.event2 = event2;
    }

    public double getDx() {
        return event2.getX() - event1.getX();
    }

    public double getDy() {
        return event2.getY() - event1.getY();
    }

    public MouseEvent getEvent1() {
        return event1;
    }

    public MouseEvent getEvent2() {
        return event2;
    }

    public static double getDiffAngle(CoordinateSystem cs, RotateEvent event) {
        return Math.atan2(event.getEvent2().getY() - cs.getY(), event.getEvent2().getX() - cs.getX())
                - Math.atan2(event.getEvent1().getY() - cs.getY(), event.getEvent1().getX() - cs.getX());
    }
}
