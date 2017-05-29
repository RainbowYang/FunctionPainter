package rainbow.inner.listener;

import rainbow.inner.system.SystemComponent;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * 所有的监听器
 *
 * @author Rainbow Yang
 */
public class Listeners implements SystemComponent {

    private List<MouseAdapter> mouseAdapters = new ArrayList<>();

    public void addMouseListener(MouseAdapter l) {
        mouseAdapters.add(l);
    }

    public void setListeners(JFrame frame) {
        mouseAdapters.forEach(listener -> {
            frame.addMouseListener(listener);
            frame.addMouseMotionListener(listener);
            frame.addMouseWheelListener(listener);
        });
    }

    @Override
    public String getKeyName() {
        return staticGetKeyName();
    }

    public static String staticGetKeyName() {
        return "Listeners";
    }

}
