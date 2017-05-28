package rainbow.outer.frame;

import rainbow.inner.system.MySystem;
import rainbow.outer.painter.BackPainter;
import rainbow.outer.painter.CoordinateSystemPainter;
import rainbow.outer.painter.MyPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import static rainbow.outer.frame.tool.FrameLocationSetter.center;

/**
 * 主界面
 *
 * @author Rainbow Yang
 */
public class MainFrame extends JFrame {
    private int x;
    private int y;

    public static MainFrame mainFrame;

    public MainFrame() throws HeadlessException {
        mainFrame = this;
        MySystem.getSystem().getVersion().setTitle(this);
        MySystem.getSystem().getSize().setSize(this);
        center(this);
        MySystem.getSystem().getListeners().setListeners(this);

        //todo 将来用View
        add(new AllPainter());


        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension d = e.getComponent().getSize();

                MySystem.getSystem().getSize().setWidth(d.getWidth());
                MySystem.getSystem().getSize().setHeight(d.getHeight());

                repaint();
            }
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);
    }

    class AllPainter extends JPanel {
        private ArrayList<MyPainter> painters = new ArrayList<>();

        //TODO 暂时
        {
            painters.add(new BackPainter());
            painters.add(new CoordinateSystemPainter());
        }

        @Override
        protected void paintComponent(Graphics g) {
            //抗锯齿
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            painters.forEach(painters -> painters.paint(g));
        }
    }
}
