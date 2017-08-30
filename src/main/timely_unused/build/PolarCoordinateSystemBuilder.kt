package rainbow.build

import com.google.gson.annotations.SerializedName
import rainbow.coordinates.CoordinateSystem
import rainbow.coordinates.PolarCoordinateSystem
import rainbow.utils.fromJson
import rainbow.utils.toJson

/**
 * @author Rainbow Yang
 */

class PolarCoordinateSystemBuilder : CoordinateSystem2DBuilder() {
    override fun build(json: String) =
            PolarCoordinateSystem().also { addDataToSystem(json.fromJson<PolarCoordinateSystemData>(), it) }

    override fun addDataToSystem(data: CoordinateSystemData, system: CoordinateSystem) {
        super.addDataToSystem(data as PolarCoordinateSystemData, system as PolarCoordinateSystem)

        if (data.axisLength != null) system.axisLength = data.axisLength!!
    }
}

class PolarCoordinateSystemData : CoordinateSystem2DData() {
    @SerializedName("Axis Length") var axisLength: Double? = null
}