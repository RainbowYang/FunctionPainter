package rainbow.build

import com.google.gson.annotations.SerializedName
import rainbow.coordinates.CoordinateSystem
import rainbow.coordinates.PolarCoordinateSystem
import rainbow.coordinates.inputName
import rainbow.coordinates.paintName
import rainbow.utils.fromJson

/**
 * @author Rainbow Yang
 */

class PolarCoordinateSystemBuilder : CoordinateSystem2DBuilder() {
    override fun build(src: String) =
            PolarCoordinateSystem().also { addDataToSystem(src.fromJson<PolarCoordinateSystemData>(), it) }


    override fun addDataToSystem(data: CoordinateSystemData, system: CoordinateSystem) {
        super.addDataToSystem(data, system)

        data as PolarCoordinateSystemData
        system as PolarCoordinateSystem

        if (data.axisLength != null) system.axisLength = data.axisLength!!
    }
}

class PolarCoordinateSystemData : CoordinateSystem2DData() {
    @SerializedName("Axis Length") var axisLength: Double? = null
}