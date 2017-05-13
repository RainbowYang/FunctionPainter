package rainbow.inner.system.comp;

import rainbow.inner.function.MyFunction;
import rainbow.inner.system.SystemComponent;

import java.util.ArrayList;

/**
 * 用于管理MyFunction
 *
 * @author Rainbow Yang
 */
public class Functions implements SystemComponent {
    private ArrayList<MyFunction> functions = new ArrayList<>();

    public Functions() {
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
