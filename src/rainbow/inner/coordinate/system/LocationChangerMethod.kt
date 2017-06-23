package rainbow.inner.coordinate.system

import rainbow.inner.coordinate.point.MyPoint
import rainbow.inner.coordinate.point.PointDouble

/**
 * @author Rainbow Yang
 */

/**
 * 把坐标点转换为屏幕上的位置

 * @param ps 坐标点
 * *
 * @return 屏幕上的位置
 */
fun CoordinateSystem.toReal(ps: List<MyPoint>): List<PointDouble> {
    val pds = mutableListOf<PointDouble>()
    ps.forEach { pds.add(toReal(it)) }
    return pds
}

fun CoordinateSystem.toReal(vararg ps: MyPoint): List<PointDouble> = toReal(ps.asList())

/**
 * 把屏幕上的位置转换为坐标点

 * @param ps 屏幕上的位置
 * *
 * @return 坐标点
 */
fun CoordinateSystem.toSystem(ps: List<PointDouble>): List<MyPoint> {
    val pds = mutableListOf<MyPoint>()
    ps.forEach { pds.add(toSystem(it)) }
    return pds
}

fun CoordinateSystem.toSystem(vararg ps: PointDouble): List<MyPoint> = toSystem(ps.asList())
