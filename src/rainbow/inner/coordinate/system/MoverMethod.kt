package rainbow.inner.coordinate.system

/**
 * @author Rainbow Yang
 */

/**
 * 在CoordinateSystem的x的基础上加上dx
 * @param dx 添加的横坐标
 */
fun CoordinateSystem.moveX(dx: Double) {
    x += dx
}

/**
 * 在CoordinateSystem的y的基础上加上dy
 * @param dy 添加的纵坐标
 */
fun CoordinateSystem.moveY(dy: Double) {
    y += dy
}

/**
 * 在CoordinateSystem的x的基础上加上dx
 * 在CoordinateSystem的y的基础上加上dy
 * @param dx 添加的横坐标
 * @param dy 添加的纵坐标
 */
fun CoordinateSystem.move(dx: Double, dy: Double) {
    x += dx
    y += dy
}

/**
 * @param x 要设置的横坐标
 * @param y 要设置的纵坐标
 */
fun CoordinateSystem.moveTo(x: Double, y: Double) {
    this.x = x
    this.y = y
}

/**
 * 把原点移到pd的位置
 * @param pd 要设置的点
 */
fun CoordinateSystem.moveTo(pd: rainbow.inner.coordinate.point.PointDouble) {
    return moveTo(pd.x, pd.y)
}

fun CoordinateSystem.moveTo(p: rainbow.inner.coordinate.point.MyPoint<*>) {
    return moveTo(toReal(p))
}

fun CoordinateSystem.moveToOpposite(p: rainbow.inner.coordinate.point.MyPoint<*>) {
    return moveTo(toReal(p.times(-1.0)))
}
