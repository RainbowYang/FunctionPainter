package rainbow.inner.coordinate.system.comp;

import rainbow.inner.coordinate.system.CoordinateSystem;
import rainbow.inner.coordinate.system.event.CoordinateSystemEvent;
import rainbow.inner.coordinate.system.event.MoveEvent;
import rainbow.inner.coordinate.system.event.RotateEvent;
import rainbow.inner.coordinate.system.event.ZoomEvent;
import rainbow.inner.system.MySystem;

/**
 * @author Rainbow Yang
 */
public class EventListener implements CoordinateSystemComponent {
    protected CoordinateSystem cs;

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

        MySystem.getSystem().getViews().repaint();
    }

    public void move(MoveEvent event) {
        cs.getMover().move(event.getDx(), event.getDy());
    }

    public void rotate(RotateEvent event) {
        cs.getAxes().addStartAngle(-RotateEvent.getDiffAngle(cs, event));
    }

    public void zoom(ZoomEvent event) {
        //todo 当实现toSystem之后 可以弄成根据鼠标中心缩放
        // MyPoint p = cs.getLocationChanger().toSystem(new PointDouble(event.getX(), event.getY()));
        // cs.getMover().moveTo(p);
        cs.getAxes().timesAllLengthTimes(Math.pow(zoomSpeed, event.getZoomLevel()));
        // cs.getMover().moveToOpposite(p);
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
