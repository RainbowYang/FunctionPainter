package rainbow.util

import java.awt.Color

/**
 * @author Rainbow Yang
 */
fun getColorByHexRGB(RGB: String?) = if (RGB != null) Color.getColor(null, Integer.decode(RGB)) else null