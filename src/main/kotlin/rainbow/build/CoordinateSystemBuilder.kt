package rainbow.build

import com.google.gson.annotations.SerializedName
import rainbow.coordinates.CoordinateSystem

abstract class CoordinateSystemBuilder {

    abstract fun build(json: String): CoordinateSystem

    open protected fun addDataToSystem(data: CoordinateSystemData, system: CoordinateSystem) {
        if (data.paintComponent?.visible != null) system.paintComponent.visible = data.paintComponent?.visible!!

        data.paintComponent?.paints?.forEach { dataPart ->
            system.paintComponent.paints.find { it.name == dataPart.name }?.apply {
                color = dataPart.color
                visible = dataPart.visible
            }
        }
    }
}

open class CoordinateSystemData {
    var type: String? = null

    open var paintComponent: PaintComponent? = null
    open var inputComponent: InputListenComponent? = null

    open class PaintComponent {
        @SerializedName("Visible") var visible: Boolean? = null
        var paints: List<CoordinateSystem.PaintComponent.PaintPart>? = null
    }

    open class InputListenComponent {
        var zoomSpeed: Double? = null
        var xAngle: Double? = null
        var yAngle: Double? = null
    }
}

