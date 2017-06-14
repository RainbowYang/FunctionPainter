package rainbow.inner.system

import java.awt.Color

/**
 * 本类用于管理颜色，你可以在这里为一些颜色注册名字
 * @author Rainbow Yang
 */
object SystemColorer {
    val colors = mapOf(
            Pair("红", "#f391a9"), Pair("黄", "#f391a9"), Pair("蓝", "#49DFEF"), Pair("绿", "#519872")
    )

    /**
     * 本方法可以接受本类中已注册的String，来得到颜色
     * 也可以使用类似于"#FFFFFF"(白色)，也可以省略最前面的"#"
     *
     * 不符合上述条件的将会返回黑色，并抛出NoSuchElementException
     *
     * @param name 共六位十六进制 ,如不符合要求，则返回黑色
     * @return 六位RGB所对应的Color
     */
    fun getColor(name: String? = null): Color {
        if (name == null) return Color.BLACK

        var RGB: String? = null
        if (colors.containsKey(name))
            RGB = colors[name]//获取"#FFFFFF"格式

        if (RGB == null) {
            return Color.BLACK
        }

        if (RGB.startsWith("#")) RGB = RGB.substring(1)
        if (RGB.length != 6) return Color.BLACK
        val i = Integer.valueOf(RGB, 16)
        return Color(i shr 16 and 0xFF, i shr 8 and 0xFF, i and 0xFF)
    }
}