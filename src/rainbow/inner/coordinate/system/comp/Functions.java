package rainbow.inner.coordinate.system.comp;

import rainbow.inner.coordinate.system.CoordinateSystem;
import rainbow.inner.function.MyFunction;
import rainbow.inner.system.MySystem;

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
        MySystem.getSystem().getInformation().log("Adding " + function.getClass().getSimpleName() + "to Functions");
        functions.add(function);
    }

    public MyFunction get(int index) {
        return functions.get(index);
    }

    public void clacFunctions() {
        MySystem.getSystem().getInformation().log("Functions is calculating.");
        functions.forEach(MyFunction::calc);
        MySystem.getSystem().getInformation().log("Functions is finishing calculating.");
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
