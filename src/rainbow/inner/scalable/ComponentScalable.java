package rainbow.inner.scalable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rainbow Yang
 */
public abstract class ComponentScalable<C extends Component> {
    protected Map<String, C> map = new HashMap<>();

    public ComponentScalable<C> setComp(C comp) {
        map.put(comp.getKeyName(), comp);
        return this;
    }

    public ComponentScalable<C> setComp(C... comps) {
        Arrays.asList(comps).forEach(comp -> setComp(comp));
        return this;
    }

    public C getComp(String key) {
        return map.get(key);
    }

}

