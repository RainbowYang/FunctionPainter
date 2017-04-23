package rainbow.inner.system.comp;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * 这是一个用于管理颜色的类
 * 默认为黑色
 *
 * @author Rainbow Yang
 * @date 2017/4/10
 */
public class Colors {
    private Map<String, Color> colorMap = new HashMap<>();

    /**
     * 用所给的String数组对对Map进行初始化，默认为Color.BLACK
     *
     * @param nameOfColors
     */
    public Colors(String... nameOfColors) {
        for (String name :
                nameOfColors) {
            colorMap.put(name, Color.BLACK);
        }
    }

    /**
     * 用所给的String数组和颜色数组对对Map进行初始化，默认为Color.BLACK
     *
     * @param nameOfColors
     */
    public Colors(String[] nameOfColors, Color[] colors) {
        for (int i = 0; i < nameOfColors.length; i++) {
            colorMap.put(nameOfColors[i], colors[i]);
        }
    }

    /**
     * 根据变量名字返回所对应的颜色，如不存在则返回BLACK，并抛出异常。
     *
     * @param name 所需要的颜色的名字
     * @return
     */
    public Color getColor(String name) {
        if (!(colorMap.keySet().contains(name))) {
            throw new NoSuchElementException("当前的Colors中不含有" + name + ",默认返回黑色");
        }
        return colorMap.getOrDefault(name, Color.BLACK);
    }

    /**
     * 新添加或改变颜色
     *
     * @param name  需要设置的颜色变量的名字
     * @param color 需要的颜色
     */
    public void setColor(String name, Color color) {
        colorMap.put(name, color);
    }

    @Override
    public String toString() {
        return "Colors{" +
                colorMap.toString() +
                '}';
    }

    /**
     * 新添加或改变颜色,颜色默认为黑色
     *
     * @param name 需要设置的颜色变量的名字
     */
    public void setColor(String name) {
        setColor(name, Color.BLACK);
    }

    /**
     * @param RGB 共六位十六进制 ,如不符合要求，则返回黑色
     * @return 六位RGB所对应的Color类
     */
    public static Color getColorByRGB(String RGB) {
        if (RGB.length() != 6) {
            return Color.BLACK;
        } else {
            Integer intval = Integer.valueOf(RGB, 16);
            int i = intval.intValue();
            return new Color((i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF);
        }
    }
}
