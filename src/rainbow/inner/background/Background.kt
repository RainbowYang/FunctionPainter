package rainbow.inner.background

import rainbow.inner.color.ColorGetter.getColor
import java.awt.Color
import java.awt.Image

/**
 * 本类用于表示背景颜色及图片
 *
 * 分为三层，为背景色，中心图片，前景色
 * 前景色仅在中心图片上面，并不会在坐标系之上
 *
 * 三者依次画出
 *
 * @author Rainbow Yang
 */
open class Background(var backColor: Color = Color.WHITE, var img: Image? = null, var frontColor: Color? = null) {
    constructor(backColor: String, img: Image? = null, frontColor: String? = null) :
            this(getColor(backColor), img, if (frontColor == null) null else getColor(frontColor))
}