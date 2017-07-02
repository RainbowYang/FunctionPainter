package rainbow.inner.coordinate.system

import rainbow.inner.color.Colors
import rainbow.inner.coordinate.system.comp.controller.CoordinateSystemController
import rainbow.inner.coordinate.system.comp.location_changer.LocationChanger
import rainbow.inner.coordinate.system.comp.painter.CoordinateSystemPainter

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
    lateinit var controller: CoordinateSystemController
    var colors = Colors()

    //todo timely
    fun getRange() = Range()
}
