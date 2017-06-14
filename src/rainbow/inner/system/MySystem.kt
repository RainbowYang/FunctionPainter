package rainbow.inner.system

import rainbow.inner.function.MyFunction
import rainbow.inner.background.Background
import rainbow.inner.coordinate.system.CoordinateSystem

/**
 * 本类单例来存贮以下东西：
 * 宽和高
 * 背景
 * 坐标系
 * 函数
 *
 * 其余所有的有关的单例都应扩展本类来获取
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
        get() = coordinateSystems[index]
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
