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
    var xAngle = 0.0
    var yAngle = 0.0


    override fun rotate(event: RotateEvent) {
        xAngle -= event.dx / 10
        yAngle += event.dy / 10

        resetAngleAndLength()
    }

    private fun resetAngleAndLength() {
        if (MySystem.coordinateSystem.axes.getSize() >= 3) {
            MySystem.coordinateSystem.axes.axes[2].lengthTimes = Math.cos(Math.toRadians(yAngle))
        }

        var x = -Math.sin(Math.toRadians(xAngle))
        var y = -Math.cos(Math.toRadians(xAngle)) * Math.sin(Math.toRadians(yAngle))

        MySystem.coordinateSystem.axes.axes[0].angle = Math.atan2(y, x)
        MySystem.coordinateSystem.axes.axes[0].lengthTimes = (Math.sqrt(x * x + y * y))

        x = -Math.sin(Math.toRadians(xAngle - 90))
        y = -Math.cos(Math.toRadians(xAngle - 90)) * Math.sin(Math.toRadians(yAngle))

        MySystem.coordinateSystem.axes.axes[1].angle = Math.atan2(y, x)
        MySystem.coordinateSystem.axes.axes[0].lengthTimes = Math.sqrt(x * x + y * y)

    }
}