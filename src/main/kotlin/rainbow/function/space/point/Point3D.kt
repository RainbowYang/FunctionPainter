package rainbow.function.space.point

/**
 * 三维点 亦可作为向量
 * @author Rainbow Yang
 */
class Point3D(val x: Double = 0.0, val y: Double = 0.0, val z: Double = 0.0) {
    constructor(x: Number = 0, y: Number = 0, z: Number = 0) :
            this(x.toDouble(), y.toDouble(), z.toDouble())

    constructor(from: Point3D) : this(from.x, from.y, from.z)

    companion object {
        val ZERO = Point3D(0, 0, 0)
    }

    val length get() = Math.sqrt(x * x + y * y + z * z)

    operator fun plus(other: Point3D) = plus(other.x, other.y, other.z)
    operator fun minus(other: Point3D) = plus(-other)
    operator fun unaryMinus() = Point3D(-x, -y, -z)

    fun plus(x: Number = 0, y: Number = 0, z: Number = 0) =
            Point3D(x.toDouble() + this.x, y.toDouble() + this.y, z.toDouble() + this.z)

    fun minus(x: Number = 0, y: Number = 0, z: Number = 0) =
            plus(-x.toDouble(), -y.toDouble(), -z.toDouble())

    operator fun times(times: Number) = times(times.toDouble())
    operator fun times(times: Double) = Point3D(x * times, y * times, z * times)

    operator fun div(divTimes: Number) = times(1 / divTimes.toDouble())

    infix fun distance(other: Point3D) = (this - other).length

    override fun toString(): String {
        return "Point3D(x=$x, y=$y, z=$z)"
    }

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