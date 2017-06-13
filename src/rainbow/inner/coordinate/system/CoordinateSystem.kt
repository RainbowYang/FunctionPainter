package rainbow.inner.coordinate.system

import rainbow.inner.coordinate.point.MyPoint
import rainbow.inner.coordinate.point.PointDouble
import rainbow.inner.coordinate.system.event.DefaultCoordinateSystemEventListener

/**
 * 坐标系
 * 主要负责坐标的换算
 *
 * @author Rainbow Yang
 */
abstract class CoordinateSystem {
    var x = rainbow.inner.system.MySystem.width / 2
    var y = rainbow.inner.system.MySystem.height / 2

    val axes = Axes()

    var eventListener = DefaultCoordinateSystemEventListener()

    abstract fun toReal(p: MyPoint): PointDouble
    abstract fun toSystem(p: PointDouble): MyPoint

    //todo timely
    fun getRange() = Range()
}
