package rainbow.inner.system;

import rainbow.inner.coordinate.system.CoordinateSystem;
import rainbow.inner.listener.Listeners;
import rainbow.inner.scalable.ComponentScalable;

import javax.swing.*;
import java.util.ArrayList;

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
        setComp(new Version(), new Size(), new Listeners());
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
}
