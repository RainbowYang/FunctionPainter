package rainbow.build

import com.google.gson.annotations.SerializedName
import rainbow.coordinates.*

/**
 * @author Rainbow Yang
 */
abstract class CoordinateSystem2DBuilder : CoordinateSystemBuilder() {
    override fun addDataToSystem(data: CoordinateSystemData, system: CoordinateSystem) {
        data as CoordinateSystem2DData
        system as CoordinateSystem2D

        if (data.x != null) system.x = data.x!!
        if (data.y != null) system.y = data.y!!
        if (data.rotatedAngle != null) system.rotatedAngle = data.rotatedAngle!!
        if (data.zoomRate != null) system.zoomRate = data.zoomRate!!

        if (data.inputComponent?.zoomSpeed != null) (system.inputComponent as CoordinateSystem2D.CoordinateSystem2DInputListener).zoomSpeed = data.inputComponent?.zoomSpeed!!
    }
}

open class CoordinateSystem2DData : CoordinateSystemData() {
    var x: Double? = null
    var y: Double? = null
    @SerializedName(rotatedAngleName) var rotatedAngle: Double? = null
    @SerializedName(zoomRateName) var zoomRate: Double? = null

    @SerializedName(inputName) var inputComponent: InputComponent? = null
    @SerializedName(paintName) var paintComponent: PaintComponent? = null

    class InputComponent {
        @SerializedName("Zoom Speed") var zoomSpeed: Double? = null
    }

    class PaintComponent {
        @SerializedName("Visible") var visible = true
        val paints = mutableListOf<CoordinateSystem.CoordinateSystemPainter.PaintPart>()
    }
}

