package rainbow.inner.scalable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rainbow Yang
 */
public abstract class ComponentScalable<C extends Component> {
    protected Map<String, C> map = new HashMap<>();

    public void setComp(C comp) {
        map.put(comp.getKeyName(), comp);
    }

    public void setComp(C... comps) {
        Arrays.asList(comps).forEach(this::setComp);
    }

    public C getComp(String key) {
        return map.get(key);
    }

}

