package rainbow.coordinates

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import rainbow.component.PaintComponent
import rainbow.utils.BufferedImage
import rainbow.utils.CoordinateGraphics
import rainbow.utils.parseColor
import java.awt.image.BufferedImage

/**
 * CoordinateSystem的绘画组件
 *
 * @author Rainbow Yang
 */
abstract class PaintComponentOfCoordinateSystem(val coordinateSystem: CoordinateSystem) : PaintComponent() {
    @Expose @SerializedName("Visible") var visible = true

    @Expose val paints = mutableListOf<PaintPart>()

    val ORIGIN: String = "Origin"
    val GRID: String = "Grid"
    val AXES: String = "Axes"
    val NUMBER: String = "Number"

    fun addPaintPart(name: String,
                     color: String = "#000000",
                     needPaint: Boolean = true,
                     paint: (CoordinateGraphics) -> Unit = {}) {
        paints.add(PaintPart(name, color, needPaint, paint))
    }

    override fun paintedImage(width: Int, height: Int): BufferedImage = BufferedImage(width, height).also {
        if (visible) {
            val cg = CoordinateGraphics(it, coordinateSystem)

            paints.filter { it.visible }.forEach {
                cg.color = parseColor(it.color)
                it.paint(cg)
            }
        }
    }

    class PaintPart(@Expose @SerializedName("Name") var name: String,
                    @Expose @SerializedName("Color") var color: String,
                    @Expose @SerializedName("Visible") var visible: Boolean,
                    var paint: (CoordinateGraphics) -> Unit)

}
