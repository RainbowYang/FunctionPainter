package rainbow.inner.coordinate.system

import rainbow.inner.color.CoordinateSystemColors
import rainbow.inner.coordinate.system.location_changer.LocationChangerOfCoordinateSystemForAxes
import rainbow.inner.coordinate.system.painter.PainterOfCoordinateSystemForAxes

/**
 * 任意维度的轴坐标系
 * @author Rainbow Yang
 */
class CoordinateSystemForAxes(size: Int) : CoordinateSystem() {
    init {
        when (size) {
            4 -> {
                axes.addAxisDeg(225)
                axes.addAxisDeg(0)
                axes.addAxisDeg(90)
                axes.addAxisDeg(135)
            }

            3 -> {
                axes.addAxisDeg(225)
                axes.addAxisDeg(0)
                axes.addAxisDeg(90)
            }

            2 -> {
                axes.addAxisDeg(0)
                axes.addAxisDeg(90)
            }

            1 -> axes.addAxisDeg(0)
        }

        locationChanger = LocationChangerOfCoordinateSystemForAxes(this)
        painter = PainterOfCoordinateSystemForAxes(this)
        colors = CoordinateSystemColors()
    }
}