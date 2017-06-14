package rainbow.inner.color

import java.awt.Color

/**
 * 一些好看的颜色
 * @author Rainbow Yang
 */
object ColorGetter {
    val colors = mapOf(Pair("红", "#f391a9"), Pair("黄", "#f391a9"), Pair("蓝", "#49DFEF"), Pair("绿", "#519872")
    )

    /**
     * @param RGB 共六位十六进制 ,如不符合要求，则返回黑色
     * @return 六位RGB所对应的Color类(允许前带"#")
     */
    fun getColor(rgb: String?): Color {
        if (rgb == null) return Color.BLACK

        if (colors.containsKey(rgb)) return getColor(colors[rgb])

        var RGB = rgb
        if (RGB.startsWith("#")) RGB = RGB.substring(1)

        if (RGB.length != 6) return Color.BLACK

        val i = Integer.valueOf(RGB, 16)
        return Color(i shr 16 and 0xFF, i shr 8 and 0xFF, i and 0xFF)
    }
}