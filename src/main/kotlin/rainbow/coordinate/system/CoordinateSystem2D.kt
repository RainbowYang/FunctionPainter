package rainbow.coordinate.system

/**
 * 二维坐标系的接口
 * 自动实现平移，旋转，伸缩
 * @author Rainbow Yang
 */
abstract class CoordinateSystem2D : CoordinateSystem {
    abstract var x: Double
    abstract var y: Double

    //使用弧度
    var rotatedAngle = 0.0

    var rotatedAngleAsDegree: Double
        get() = Math.toDegrees(rotatedAngle)
        set(value) {
            rotatedAngle = Math.toRadians(value)
        }

    var zoomRate = 1.0

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
}