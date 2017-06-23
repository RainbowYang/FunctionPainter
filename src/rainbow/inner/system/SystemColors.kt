package rainbow.inner.system

import rainbow.inner.color.Colors
import java.awt.Color

/**
 * 本类管理所有的颜色设置
 * @author Rainbow Yang
 */
object SystemColors {
    var colorsList = mutableListOf<Colors>()
    val colors = mapOf(
            Pair("红", "#f391a9"), Pair("黄", "#f391a9"), Pair("蓝", "#49DFEF"), Pair("绿", "#519872")
    )

    /**
     * 本方法可以接受本类中已注册的String，来得到颜色
     * 本也可以接受本类中colorsList中已注册的String，来得到颜色
     * 也可以使用类似于"#FFFFFF"(白色)，也可以省略最前面的"#"
     *
     * 不符合上述条件的将会返回黑色，并抛出NoSuchElementException
     *
     * @param name 共六位十六进制 ,如不符合要求，则返回黑色
     * @return 六位RGB所对应的Color
     */
    fun getColor(name: String? = null): Color {
        if (name == null) return Color.BLACK

        colorsList.forEach {
            if (it.colorMap.containsKey(name))
                return it.getColor(name)
        }

        if (colors.containsKey(name))
            return getColorByName(name)

        return getColorByRGB(name)
    }

    private fun getColorByRGB(rgb: String): Color {
        var RGB = rgb

        if (RGB.startsWith("#")) RGB = RGB.substring(1)

        if (RGB.length != 6) {
            throw IllegalColorFormatException(RGB)
        }
        val i = Integer.valueOf(RGB, 16)
        return Color(i shr 16 and 0xFF, i shr 8 and 0xFF, i and 0xFF)
    }


    private fun getColorByName(name: String): Color {
        if (colors.containsKey(name)) return getColorByRGB(colors[name]!!)
        throw NoSuchElementException("$name 并未定义")
    }

    class IllegalColorFormatException(message: String?) : RuntimeException(message)
}
