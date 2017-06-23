package rainbow.inner.coordinate.system.event

import rainbow.inner.coordinateSystem.system.event.CoordinateSystemEventListener
import rainbow.inner.system.MySystem

/**
 * @author Rainbow Yang
 */
class DefaultCoordinateSystemEventListener : CoordinateSystemEventListener {
    //        fun rotate(event: RotateEvent) {
//        MySystem.coordinateSystem.axes.startAngle += (-RotateEvent.getDiffAngle(MySystem.coordinateSystem, event))
//    }
    var xAngle = 30.0
    var yAngle = 30.0


    override fun rotate(event: RotateEvent) {
        xAngle -= event.dx / 10
        yAngle += event.dy / 10

        resetAngleAndLength()
    }

    private fun resetAngleAndLength() {
        MySystem.coordinateSystem.axes.setLengthTimes(2, Math.cos(Math.toRadians(yAngle)))

        var x = -Math.sin(Math.toRadians(xAngle))
        var y = -Math.cos(Math.toRadians(xAngle)) * Math.sin(Math.toRadians(yAngle))

        MySystem.coordinateSystem.axes.setAngle(0, Math.atan2(y, x))
        MySystem.coordinateSystem.axes.setLengthTimes(0, Math.sqrt(x * x + y * y))

        x = -Math.sin(Math.toRadians(xAngle - 90))
        y = -Math.cos(Math.toRadians(xAngle - 90)) * Math.sin(Math.toRadians(yAngle))

        MySystem.coordinateSystem.axes.setAngle(1, Math.atan2(y, x))
        MySystem.coordinateSystem.axes.setLengthTimes(1, Math.sqrt(x * x + y * y))

    }
}