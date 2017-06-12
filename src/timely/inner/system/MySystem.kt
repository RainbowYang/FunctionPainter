package timely.inner.system

import rainbow.inner.coordinate.system.CoordinateSystem
import rainbow.inner.function.MyFunction
import timely.inner.background.Background

/**
 * 本类单例来存贮以下东西：
 * 宽和高
 * 背景
 * 坐标系
 * 函数
 *
 * @author Rainbow Yang
 * @see Background
 * @see CoordinateSystem
 * @see MyFunction
 */
object MySystem {
    var width: Double = 1300.0
    var height: Double = 700.0

    var background = Background()

    //目前使用的CoordinateSystem的索引
    private var index = -1
    val coordinateSystems = mutableListOf<CoordinateSystem>()

    var coordinateSystem: CoordinateSystem
        get() = coordinateSystems.get(index)
        set(cs) {
            if (coordinateSystems.contains(cs)) {
                index = coordinateSystems.indexOf(cs)
            } else {
                coordinateSystems.add(cs)
                index = coordinateSystems.size - 1
            }
        }

    //所有的函数
    val functions = mutableListOf<MyFunction>()
}
