package rainbow.inner.system;

import rainbow.inner.coordinate.system.CoordinateSystem;
import rainbow.inner.listener.Listeners;
import rainbow.inner.scalable.ComponentScalable;
import rainbow.inner.view.BackgroundView;
import rainbow.inner.view.CoordinateSystemView;
import rainbow.inner.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * 本类采用单例模式
 * 用于获取一些对象
 * 并存储所有的CoordinateSystem
 *
 * @author Rainbow Yang
 * @see rainbow.inner.system.MySystem.Version
 * @see rainbow.inner.system.MySystem.Size
 */
public class MySystem extends ComponentScalable<SystemComponent> {

    private static MySystem system = new MySystem();

    private ArrayList<CoordinateSystem> CoordinateSystems = new ArrayList<>();
    private int index = -1;//目前使用的CoordinateSystem的索引

    private MySystem() {
        setComp(new Information(), new Version(), new Size(), new Listeners(), new Views());
        getInformation().initEndLog("System");
    }


    public CoordinateSystem getCoordinateSystem() {
        return CoordinateSystems.get(index);
    }

    public void setCoordinateSystem(CoordinateSystem cs) {
        if (CoordinateSystems.contains(cs)) {
            index = CoordinateSystems.indexOf(cs);
        } else {
            CoordinateSystems.add(cs);
            index = CoordinateSystems.size() - 1;
        }
    }

    public ArrayList<CoordinateSystem> getCoordinateSystems() {
        return CoordinateSystems;
    }

    //start-getComp

    public Version getVersion() {
        return (Version) getComp(Version.staticGetKeyName());
    }

    public Listeners getListeners() {
        return (Listeners) getComp(Listeners.staticGetKeyName());
    }

    public Size getSize() {
        return (Size) getComp(Size.staticGetKeyName());
    }

    public Views getViews() {
        return (Views) getComp(Views.staticGetKeyName());
    }

    public Information getInformation() {
        return (Information) getComp(Information.staticGetKeyName());
    }

    //end-getComp

    public static MySystem getSystem() {
        return system;
    }

    // inner class

    /**
     * 本类用于存储所要显示的大小。
     *
     * @author Rainbow Yang
     */
    public static class Size implements SystemComponent {
        public static final double DEFAULT_WIDTH = 1300, DEFAULT_HEIGHT = 800;

        private double width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;

        public double getWidth() {
            return width;
        }

        public void setWidth(double newWidth) {
            width = newWidth;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double newHeight) {
            height = newHeight;
        }

        public void setSize(JFrame f) {
            f.setSize((int) getWidth(), (int) getHeight());
        }

        @Override
        public String getKeyName() {
            return staticGetKeyName();
        }

        public static String staticGetKeyName() {
            return "Size";
        }
    }

    /**
     * 本类用于存储版本信息。
     *
     * @author Rainbow Yang
     */
    public static class Version implements SystemComponent {
        private String name = "FunctionPainter";
        private String version = "V2.0";
        private String author = "Rainbow Yang";

        public String getName() {
            return name;
        }

        public String getVersion() {
            return version;
        }

        public String getAuthor() {
            return author;
        }

        public void setTitle(JFrame f) {
            f.setTitle(getName() + getVersion() + "  Author:" + getAuthor());
        }

        @Override
        public String getKeyName() {
            return staticGetKeyName();
        }

        public static String staticGetKeyName() {
            return "Version";
        }
    }

    /**
     * 本类用于存储界面
     */
    public static class Views implements SystemComponent {
        private Runnable repaint;

        private ArrayList<View> views = new ArrayList<>();

        {
            addView(new BackgroundView());
            addView(new CoordinateSystemView());
        }

        public void setRepaint(Runnable repaint) {
            this.repaint = repaint;
        }

        public Image getImage() {
            BufferedImage image = new BufferedImage((int) MySystem.getSystem().getSize().getWidth(),
                    (int) MySystem.getSystem().getSize().getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
            Graphics g = image.getGraphics();
            //抗锯齿
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            views.forEach(view -> view.paint(g));
            return image;
        }

        public void addView(View view) {
            views.add(view);
        }

        public void repaint() {
            repaint.run();
        }

        @Override
        public String getKeyName() {
            return staticGetKeyName();
        }

        public static String staticGetKeyName() {
            return "Views";
        }

    }

    /**
     * 本类用于存储信息
     */
    public static class Information implements SystemComponent {
        private List<String> informations = new ArrayList<>();

        private long start;

        {
            System.out.println();
            System.out.println("Time\tProcess");
            start = System.currentTimeMillis();
            initStartLog("System");
        }

        public void log(String information) {
            informations.add((System.currentTimeMillis() - start) + "\t\t" + information);
            System.out.println(informations.get(informations.size() - 1));
        }

        public void initStartLog(String information) {
            log("Start initing " + information + "...");
        }

        public void initEndLog(String information) {
            log("Finish initing " + information + "...");
        }

        @Override
        public String getKeyName() {
            return staticGetKeyName();
        }

        public static String staticGetKeyName() {
            return "Information";
        }
    }
}
