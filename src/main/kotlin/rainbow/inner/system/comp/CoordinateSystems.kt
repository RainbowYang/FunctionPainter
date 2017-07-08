package rainbow.inner.system.comp

import rainbow.coordinate.system.CoordinateSystem

/**
 * @author Rainbow Yang
 */
class CoordinateSystems {
    //目前使用的CoordinateSystem的索引
    private var index = -1
    val coordinateSystems = mutableListOf<CoordinateSystem>()

    var coordinateSystem: CoordinateSystem
        get() = coordinateSystems[index]
        set(cs) {
            if (coordinateSystems.contains(cs)) {
                index = coordinateSystems.indexOf(cs)
            } else {
                coordinateSystems.add(cs)
                index = coordinateSystems.size - 1
            }
        }
}