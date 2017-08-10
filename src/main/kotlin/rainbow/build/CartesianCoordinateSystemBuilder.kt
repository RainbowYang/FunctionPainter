package rainbow.build

import rainbow.coordinates.*
import rainbow.utils.fromJson

abstract class CartesianCoordinateSystemBuilder : CoordinateSystem2DBuilder()

class CartesianCoordinateSystemClassicBuilder : CartesianCoordinateSystemBuilder() {
    override fun build(json: String) = CartesianCoordinateSystemClassic().also {
        addDataToSystem(json.fromJson<CartesianCoordinateSystemClassicData>(), it)
    }

    override fun addDataToSystem(data: CoordinateSystemData, system: CoordinateSystem) {
        super.addDataToSystem(data, system)

        data as CartesianCoordinateSystemClassicData
        system as CartesianCoordinateSystemClassic

        if (data.axes != null) data.axes?.forEach { system.axes.add(it) }
    }
}

open class CartesianCoordinateSystemClassicData : CoordinateSystem2DData() {
    var axes: MutableList<CartesianCoordinateSystemClassic.ClassicAxis>? = null
}

class CartesianCoordinateSystemBallBuilder : CartesianCoordinateSystemBuilder() {

    override fun build(json: String) = CartesianCoordinateSystemBall().also {
        addDataToSystem(json.fromJson<CartesianCoordinateSystemBallData>(), it)
    }

    override fun addDataToSystem(data: CoordinateSystemData, system: CoordinateSystem) {
        super.addDataToSystem(data, system)

        data as CartesianCoordinateSystemBallData
        system as CartesianCoordinateSystemBall

        if (data.axes != null) data.axes?.forEach { system.axes.add(it) }

        if (data.inputComponent != null) {
            val systemInput = system.inputComponent as CartesianCoordinateSystem.InputListenComponent
            val dataInput = data.inputComponent as CoordinateSystemData.InputListenComponent

            if (dataInput.xAngle != null) systemInput.xAngle = dataInput.xAngle!!
            if (dataInput.yAngle != null) systemInput.yAngle = dataInput.yAngle!!
        }
    }

}

open class CartesianCoordinateSystemBallData : CoordinateSystem2DData() {
    var axes: MutableList<CartesianCoordinateSystemBall.BallAxis>? = null
}
