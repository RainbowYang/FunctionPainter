package rainbow.point

/**
 * 三维轴坐标点
 * @author Rainbow Yang
 */
class Point3D(val x: Double = 0.0, val y: Double = 0.0, val z: Double = 0.0) : CoordinatePoint {
    constructor(x: Number = 0, y: Number = 0, z: Number = 0) : this(x.toDouble(), y.toDouble(), z.toDouble())

    operator fun component1() = x
    operator fun component2() = y
    operator fun component3() = z

    companion object {
        operator fun invoke(form: CoordinatePoint): Point3D {
            val (x, y, z) = form.asAxes
            return Point3D(x, y, z)
        }

        val ZERO = Point3D(0.0, 0.0, 0.0)
    }

    override val asAxes by lazy { PointAxes(x, y, z) }

    fun spinAtXY(angle: Number) = this.asAxes.spinAtAndNew(0, 1, angle)
    fun spinAtXZ(angle: Number) = this.asAxes.spinAtAndNew(0, 2, angle)
    fun spinAtYX(angle: Number) = this.asAxes.spinAtAndNew(1, 0, angle)
    fun spinAtYZ(angle: Number) = this.asAxes.spinAtAndNew(1, 2, angle)
    fun spinAtZX(angle: Number) = this.asAxes.spinAtAndNew(2, 0, angle)
    fun spinAtZY(angle: Number) = this.asAxes.spinAtAndNew(2, 1, angle)

    override fun toString() = "Point3D(x=$x, y=$y, z=$z)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Point3D

        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + z.hashCode()
        return result
    }


}