package rainbow.inner.system.comp;

import rainbow.inner.function.MyFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用于管理MyFunction
 *
 * @author Rainbow Yang
 * @date 2017/4/12
 */
public class Functions {
    private List<MyFunction> functions = new ArrayList<>();

    public Functions(MyFunction... fs) {
        Collections.addAll(functions, fs);
    }

    public void addFunction(MyFunction f) {
        functions.add(f);
    }

    public List<MyFunction> getFunctions() {
        return functions;
    }

    @Override
    public String toString() {
        return "Functions{" +
                "functions=" + functions +
                '}';
    }
}
