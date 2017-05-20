package rainbow.inner.coordinate.system.comp;

import rainbow.inner.coordinate.system.CoordinateSystem;
import rainbow.inner.coordinate.system.event.CoordinateSystemEvent;
import rainbow.inner.coordinate.system.event.MoveEvent;
import rainbow.inner.coordinate.system.event.RotateEvent;
import rainbow.inner.coordinate.system.event.ZoomEvent;
import rainbow.outer.frame.MainFrame;

/**
 * @author Rainbow Yang
 */
public class EventListener implements CoordinateSystemComponent {
    private CoordinateSystem cs;

    //todo 暂时
    private double zoomSpeed = 1.1;

    public EventListener(CoordinateSystem cs) {
        this.cs = cs;
    }

    public void accept(CoordinateSystemEvent event) {
        if (event instanceof MoveEvent) {
            move((MoveEvent) event);
        } else if (event instanceof RotateEvent) {
            rotate((RotateEvent) event);
        } else if (event instanceof ZoomEvent) {
            zoom((ZoomEvent) event);
        }

        specialAccept(event);

        // todo repaint 需改
        MainFrame.mainFrame.repaint();
    }

    public void move(MoveEvent event) {
        cs.getMover().move(event.getDx(), event.getDy());
    }

    public void rotate(RotateEvent event) {
        cs.getAxes().addStartAngle(-RotateEvent.getDiffAngle(cs, event));
    }

    public void zoom(ZoomEvent event) {
        cs.getAxes().timesLengthTimes(Math.pow(zoomSpeed, event.getZoomLevel()));
    }

    /**
     * 用于子类扩展。
     *
     * @param event
     */
    public void specialAccept(CoordinateSystemEvent event) {
    }

    @Override
    public String getKeyName() {
        return staticGetKeyName();
    }

    public static String staticGetKeyName() {
        return "EventListener";
    }
}
