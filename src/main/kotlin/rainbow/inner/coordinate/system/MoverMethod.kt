package rainbow.inner.coordinate.system

import rainbow.inner.coordinate.point.CoordinatePoint
import rainbow.inner.coordinate.point.PointDouble

/**
 * @author Rainbow Yang
 */
fun CoordinateSystem.moveX(dx: Double) {
    x += dx
}

fun CoordinateSystem.moveY(dy: Double) {
    y += dy
}

fun CoordinateSystem.move(dx: Double, dy: Double) {
    x += dx
    y += dy
}

fun CoordinateSystem.moveTo(x: Double, y: Double) {
    this.x = x
    this.y = y
}

fun CoordinateSystem.move(dx: Int, dy: Int) = move(dx.toDouble(), dy.toDouble())

fun CoordinateSystem.moveTo(x: Int, y: Int) = moveTo(x.toDouble(), y.toDouble())


fun CoordinateSystem.moveTo(pd: PointDouble) {
    return moveTo(pd.x, pd.y)
}

fun CoordinateSystem.moveTo(p: CoordinatePoint) {
    return moveTo(toScreen(p))
}

fun CoordinateSystem.moveToOpposite(p: CoordinatePoint) {
    return moveTo(toScreen(p.times(-1.0)))
}
