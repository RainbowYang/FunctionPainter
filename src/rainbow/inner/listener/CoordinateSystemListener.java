package rainbow.inner.listener;

import rainbow.inner.coordinate.system.CoordinateSystem;
import rainbow.inner.system.MySystem;
import rainbow.outer.frame.MainFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * @author Rainbow Yang
 */
public class CoordinateSystemListener extends MouseAdapter {
    //记录点击的点
    private double dx, dy;

    //缩放倍数
    private double times = 1.1;

    private CoordinateSystem cs = MySystem.getSystem().getCoordinateSystem();

    public void reGetCoordinateSystem() {
        cs = MySystem.getSystem().getCoordinateSystem();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        reGetCoordinateSystem();
        if (e.getButton() == MouseEvent.BUTTON1) {
            dx = cs.getX() - e.getX();
            dy = cs.getY() - e.getY();
        }
    }

    //坐标系移动
    @Override
    public void mouseDragged(MouseEvent e) {
        reGetCoordinateSystem();
        cs.setX(e.getX() + dx);
        cs.setY(e.getY() + dy);
        MainFrame.mainFrame.repaint();
    }

    //缩放效果
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        reGetCoordinateSystem();
        if (e.getWheelRotation() > 0) {
            cs.setLengths(times);
        } else {
            cs.setLengths(1 / times);
        }
        MainFrame.mainFrame.repaint();
    }
}
