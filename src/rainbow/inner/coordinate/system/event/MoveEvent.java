package rainbow.inner.coordinate.system.event;

import java.awt.event.MouseEvent;

/**
 * @author Rainbow Yang
 */
public class MoveEvent implements CoordinateSystemEvent {
    private MouseEvent event1;
    private MouseEvent event2;

    public MoveEvent(MouseEvent event1, MouseEvent event2) {
        this.event1 = event1;
        this.event2 = event2;
    }

    public double getDx() {
        return event2.getX() - event1.getX();
    }

    public double getDy() {
        return event2.getY() - event1.getY();
    }
}
