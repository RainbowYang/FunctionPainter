package rainbow.inner.coordinate.system.event;

import java.awt.event.MouseWheelEvent;

/**
 * @author Rainbow Yang
 */
public class ZoomEvent implements CoordinateSystemEvent {
    private MouseWheelEvent event;

    public ZoomEvent(MouseWheelEvent event) {
        this.event = event;
    }

    public double getZoomLevel() {
        return event.getWheelRotation();
    }
}
