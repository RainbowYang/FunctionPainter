package rainbow.inner.coordinate.system

import rainbow.inner.color.Colors
import rainbow.inner.coordinate.system.event.DefaultCoordinateSystemEventListener
import rainbow.inner.coordinate.system.location_changer.LocationChanger
import rainbow.inner.coordinate.system.painter.CoordinateSystemPainter

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

    lateinit var painter: CoordinateSystemPainter
    lateinit var locationChanger: LocationChanger
    var colors = Colors()

    var eventListener = DefaultCoordinateSystemEventListener()
    //todo timely
    fun getRange() = Range()
}
