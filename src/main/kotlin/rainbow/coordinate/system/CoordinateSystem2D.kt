package rainbow.coordinate.system

import rainbow.coordinate.point.PointDouble

/**
 * 二维坐标系的接口
 * 实现平移，旋转，伸缩
 * @author Rainbow Yang
 */
abstract class CoordinateSystem2D(var x: Double = 0.0,
                                  var y: Double = 0.0,
                                  var rotatedAngle: Double = 0.0,
                                  var zoomRate: Double = 1.0
) : CoordinateSystem {

    var rotatedAngleAsDegree: Double
        get() = Math.toDegrees(rotatedAngle)
        set(value) {
            rotatedAngle = Math.toRadians(value)
        }

    fun move(x: Number, y: Number) {
        this.x += x.toDouble()
        this.y += y.toDouble()
    }

    fun rotate(angle: Number) {
        rotatedAngle += angle.toDouble()
    }

    fun zoom(times: Number) {
        zoomRate *= times.toDouble()
    }

    fun rotateAndScaleAndMove(pd: PointDouble): PointDouble {
        var result = pd
        if (rotatedAngle != 0.0)
            result = result.spin(rotatedAngle)

        if (zoomRate != 1.0)
            result = result.times(zoomRate)

        result.x = x + result.x
        // 屏幕的点是向右下角递增,而坐标点是向右上角递增
        result.y = y - result.y

        return result
    }

    fun inverseRotateAndScaleAndMove(pd: PointDouble): PointDouble {
        var result = pd
        result.x = result.x - x
        // 屏幕的点是向右下角递增,而坐标点是向右上角递增
        result.y = y - result.y

        if (rotatedAngle != 0.0)
            result = result.spin(-rotatedAngle)

        if (zoomRate != 1.0)
            result = result.times(1.0 / zoomRate)
        return result
    }
}