package rainbow.inner.coordinate.system.event;

import java.awt.event.MouseWheelEvent;

/**
 * 坐标系缩放事件
 *
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

    public double getX() {
        return event.getX();
    }

    public double getY() {
        return event.getY();
    }
}
