package rainbow.build

import rainbow.coordinates.CoordinateSystem
import rainbow.coordinates.CoordinateSystem2D

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

        if (data.inputComponent?.zoomSpeed != null) {
            (system.inputComponent as CoordinateSystem2D.InputListenComponent)
                    .zoomSpeed = data.inputComponent?.zoomSpeed!!
        }
    }
}

open class CoordinateSystem2DData : CoordinateSystemData() {
    var x: Double? = null
    var y: Double? = null

    var rotatedAngle: Double? = null
    var zoomRate: Double? = null

}

