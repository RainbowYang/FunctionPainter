package rainbow.outer.frame;

import rainbow.inner.system.MySystem;
import rainbow.outer.painter.BackPainter;
import rainbow.outer.painter.CoordinateSystemPainter;
import rainbow.outer.painter.FunctionPainter;
import rainbow.outer.painter.MyPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static rainbow.outer.frame.tool.FrameLocationSetter.center;

/**
 * @author Rainbow Yang
 */
public class MainFrame extends JFrame {
    private int x;
    private int y;

    public static MainFrame mainFrame;

    public MainFrame() throws HeadlessException {
        mainFrame = this;
        setTitle(MySystem.name + MySystem.version + "  Author:Rainbow Yang");
        setSize((int) MySystem.getWidth(), (int) MySystem.getHeight());
        center(this);

        add(new AllPainter());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = (int) MySystem.getSystem().getCoordinateSystem().getX() - e.getX();
                y = (int) MySystem.getSystem().getCoordinateSystem().getY() - e.getY();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                MySystem.getSystem().getCoordinateSystem().setX(e.getX() + x);
                MySystem.getSystem().getCoordinateSystem().setY(e.getY() + y);
                mainFrame.repaint();
            }
        });
        addMouseWheelListener(e -> {

        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    class AllPainter extends JPanel {
        private ArrayList<MyPainter> painters = new ArrayList<>();

        //TODO 暂时
        {
            painters.add(new BackPainter());
            painters.add(new CoordinateSystemPainter());
            painters.add(new FunctionPainter());
        }

        @Override
        protected void paintComponent(Graphics g) {
            //抗锯齿
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            painters.forEach(painters -> painters.paint(g));
        }
    }
}
