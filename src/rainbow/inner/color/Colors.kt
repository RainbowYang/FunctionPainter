package rainbow.inner.color

import java.awt.Color
import java.util.HashMap
import java.util.NoSuchElementException

/**
 * @author Rainbow Yang
 */
class Colors {
    private val colorMap = HashMap<String, String>()

    /**
     * 用所给的String数组对对Map进行初始化，默认为Color.BLACK

     * @param nameOfColors
     */
    constructor(vararg nameOfColors: String) {
        for (name in nameOfColors) {
            colorMap.put(name, "#000000")
        }
    }

    /**
     * 用所给的String数组和颜色数组对对Map进行初始化，默认为Color.BLACK

     * @param nameOfColors
     */
    constructor(nameOfColors: Array<String>, colors: Array<String>) {
        for (i in nameOfColors.indices) {
            colorMap.put(nameOfColors[i], colors[i])
        }
    }

    /**
     * 根据变量名字返回所对应的颜色，如不存在则返回BLACK，并抛出异常。

     * @param name 所需要的颜色的名字
     * *
     * @return
     */
    fun getColor(name: String): Color {
        if (!colorMap.keys.contains(name)) {
            throw NoSuchElementException("当前的Colors中不含有$name,默认返回黑色")
        }
        return ColorGetter.getColor(colorMap.getOrDefault(name, "#000000"))
    }

    /**
     * 新添加或改变颜色

     * @param name  需要设置的颜色变量的名字
     * *
     * @param color 需要的颜色
     */
    @JvmOverloads fun setColor(name: String, color: String = "#000000") {
        colorMap.put(name, color)
    }

    override fun toString(): String {
        return "Colors{" +
                colorMap.toString() +
                '}'
    }
}