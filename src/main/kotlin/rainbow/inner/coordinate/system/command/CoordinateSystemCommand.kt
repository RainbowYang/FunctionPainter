package rainbow.inner.coordinate.system.command

import rainbow.inner.coordinate.system.CoordinateSystem

/**
 * 用于控制CoordinateSystem的命令
 * 应实现一个 (S:CoordinateSystem)->Unit 的方法来对其进行修改
 * @author Rainbow Yang
 */
abstract class CoordinateSystemCommand<in S : CoordinateSystem> {
    abstract fun todo(system: S)
}
