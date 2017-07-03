package rainbow.inner.coordinate.system

import rainbow.inner.coordinate.point.CoordinatePoint
import rainbow.inner.coordinate.point.PointDouble
import rainbow.inner.coordinate.system.command.CoordinateSystemCommand
import rainbow.inner.coordinate.system.command.WorryCoordinateSystemToCommandException

/**
 * 坐标系的顶层抽象类
 * 负责坐标的换算
 * 分为toScreen(MyPoint)和toSystem(PointDouble)
 *
 * 在这里，对点进行平移，旋转，缩放
 * 子类无需关心此事
 *
 * @author Rainbow Yang
 */
abstract class CoordinateSystem {
    var origin = PointDouble(500, 300)
    var x: Double
        get() = origin.x
        set(value) {
            origin.x = value
        }
    var y: Double
        get() = origin.y
        set(value) {
            origin.y = value
        }

    //使用弧度
    var rotateAngle = 0.0

    var rotateAngleAsDegree: Double
        get() = Math.toDegrees(rotateAngle)
        set(value) {
            rotateAngle = Math.toRadians(value)
        }

    var zoomRate = 1.0


    fun toReal(ps: List<CoordinatePoint>): List<PointDouble> = toScreen(ps)
    fun toReal(p: CoordinatePoint): PointDouble = toScreen(p)

    fun toScreen(cps: List<CoordinatePoint>): List<PointDouble> {
        val pds = mutableListOf<PointDouble>()
        cps.forEach { pds.add(toScreen(it)) }
        return pds
    }

    fun toScreen(cp: CoordinatePoint): PointDouble = rotateAndScaleAndMove(_toScreen(cp))

    protected abstract fun _toScreen(cp: CoordinatePoint): PointDouble

    fun rotateAndScaleAndMove(pd: PointDouble): PointDouble {
        var result = pd
        if (rotateAngle != 0.0)
            result = result.spin(rotateAngle)

        if (zoomRate != 1.0)
            result = result.times(zoomRate)

        result.x = origin.x + result.x
        // 屏幕的点是向右下角递增,而坐标点是向右上角递增
        result.y = origin.y - result.y

        return result
    }

    fun toSystem(pds: List<PointDouble>): List<CoordinatePoint> {
        val cps = mutableListOf<CoordinatePoint>()
        pds.forEach { cps.add(toSystem(it)) }
        return cps
    }

    fun toSystem(pd: PointDouble): CoordinatePoint {
        return _toSystem(inverseRotateAndScaleAndMove(pd))
    }

    protected abstract fun _toSystem(p: PointDouble): CoordinatePoint

    fun inverseRotateAndScaleAndMove(pd: PointDouble): PointDouble {
        var result = pd
        result.x = result.x - origin.x
        // 屏幕的点是向右下角递增,而坐标点是向右上角递增
        result.y = origin.y - result.y

        if (rotateAngle != 0.0)
            result = result.spin(-rotateAngle)

        if (zoomRate != 1.0)
            result = result.times(1.0 / zoomRate)
        return result
    }

    inline fun <reified S : CoordinateSystem> doCommand(command: CoordinateSystemCommand<S>) {
        if (this is S)
            command.todo(this)
        else
            throw WorryCoordinateSystemToCommandException("$command isn't suitable for $this")
    }

    override fun toString(): String {
        return "CoordinateSystem(origin=$origin, rotateAngle=$rotateAngle, zoomRate=$zoomRate)"
    }


}
