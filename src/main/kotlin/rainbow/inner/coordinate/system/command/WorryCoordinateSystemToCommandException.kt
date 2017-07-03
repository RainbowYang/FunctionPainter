package rainbow.inner.coordinate.system.command

import rainbow.inner.coordinate.system.CoordinateSystem

/**
 * 当所使用的CoordinateSystemCommand的适用CoordinateSystem并不是它本身或其父类时，抛出此异常.
 * @author Rainbow Yang
 */
class WorryCoordinateSystemToCommandException(message: String) : RuntimeException(message)
