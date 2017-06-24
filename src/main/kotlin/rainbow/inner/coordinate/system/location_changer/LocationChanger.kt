package rainbow.inner.coordinate.system.location_changer

import rainbow.inner.coordinate.point.MyPoint
import rainbow.inner.coordinate.point.PointDouble

/**
 * 坐标转换
 * @author Rainbow Yang
 */
interface LocationChanger {
    /**
     * 把坐标点转换为屏幕上的位置
     */
    fun toReal(p: MyPoint): PointDouble

    fun toReal(ps: List<MyPoint>): List<PointDouble> {
        val pds = mutableListOf<PointDouble>()
        ps.forEach { pds.add(toReal(it)) }
        return pds
    }

    fun toReal(vararg ps: MyPoint): List<PointDouble> = toReal(ps.asList())


    /**
     * 把屏幕上的位置转换为坐标点
     */
    fun toSystem(p: PointDouble): MyPoint

    fun toSystem(ps: List<PointDouble>): List<MyPoint> {
        val pds = mutableListOf<MyPoint>()
        ps.forEach { pds.add(toSystem(it)) }
        return pds
    }

    fun toSystem(vararg ps: PointDouble): List<MyPoint> = toSystem(ps.asList())

}