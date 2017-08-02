package rainbow.function.space.body

import rainbow.point.Point3D
import rainbow.utils.G
import rainbow.utils.asPoint3D

/**
 * 是一个有质量的物体
 * 并拥有速度和位置
 *
 * 单位暂时不设置 均为国际标准单位
 * @author Rainbow Yang
 */
abstract class Body(var location: Point3D = Point3D.ZERO,
                    var velocity: Point3D = Point3D.ZERO,
                    var mass: Double = 0.0) {
    /**
     * 以当前速度运行[time]秒
     */
    fun move(time: Double) {
        location = (location + velocity * time).asPoint3D
    }

    infix fun gravityFrom(from: Body): Point3D {

        val relativeLocation = from.location - location

        val distance = relativeLocation.length

        return ((relativeLocation / distance) * G * mass * from.mass / (distance * distance)).asPoint3D

    }

    override fun toString(): String {
        return "Body(location=$location, velocity=$velocity, mass=$mass)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Body

        if (location != other.location) return false
        if (velocity != other.velocity) return false
        if (mass != other.mass) return false

        return true
    }

    override fun hashCode(): Int {
        var result = location.hashCode()
        result = 31 * result + velocity.hashCode()
        result = 31 * result + mass.hashCode()
        return result
    }

}
