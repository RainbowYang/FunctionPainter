package rainbow.build

import com.google.gson.annotations.SerializedName
import rainbow.coordinates.CoordinateSystem
import rainbow.coordinates.typeName

abstract class CoordinateSystemBuilder {
    abstract fun build(src: String): CoordinateSystem

    protected abstract fun addDataToSystem(data: CoordinateSystemData, system: CoordinateSystem)

}

open class CoordinateSystemData(@SerializedName(typeName) var type: String? = null)

