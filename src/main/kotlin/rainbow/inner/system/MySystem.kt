package rainbow.inner.system

import rainbow.inner.function.MyFunction
import rainbow.inner.background.Background
import rainbow.inner.coordinate.system.CoordinateSystem
import rainbow.inner.system.comp.CoordinateSystems
import rainbow.inner.system.comp.Functions

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

    val coordinateSystems = CoordinateSystems()

    var coordinateSystem
        get() = coordinateSystems.coordinateSystem
        set(cs) {
            coordinateSystems.coordinateSystem = cs
        }

    //所有的函数
    val Functions = Functions()

    val functions get() = Functions.functions
}
