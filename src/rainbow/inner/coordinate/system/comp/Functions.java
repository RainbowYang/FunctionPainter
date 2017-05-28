package rainbow.inner.coordinate.system.comp;

import rainbow.inner.coordinate.system.CoordinateSystem;
import rainbow.inner.function.MyFunction;

import java.util.ArrayList;

/**
 * 用于管理MyFunction
 *
 * @author Rainbow Yang
 */
public class Functions implements CoordinateSystemComponent {
    private ArrayList<MyFunction> functions = new ArrayList<>();

    private CoordinateSystem cs;

    public Functions(CoordinateSystem cs) {
        this.cs = cs;
    }

    public void add(MyFunction function) {
        functions.add(function);
    }

    public MyFunction get(int index) {
        return functions.get(index);
    }

    public ArrayList<MyFunction> getFunctions() {
        return functions;
    }

    @Override
    public String toString() {
        return "Functions{" + functions + '}';
    }

    public String getKeyName() {
        return staticGetKeyName();
    }

    public static String staticGetKeyName() {
        return "Functions";
    }
}
