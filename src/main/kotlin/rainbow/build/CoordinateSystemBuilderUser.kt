package rainbow.build

import rainbow.coordinates.CoordinateSystem
import rainbow.coordinates.PolarCoordinateSystem
import rainbow.utils.fromJson
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance


/**
 * 坐标系构建器
 * @author Rainbow Yang
 */
object CoordinateSystemBuilderUser {

    val builders: HashMap<KClass<out CoordinateSystem>, KClass<out CoordinateSystemBuilder>> = hashMapOf()

    init {
        builders.put(PolarCoordinateSystem::class, PolarCoordinateSystemBuilder::class)
    }

    fun build(src: String): CoordinateSystem {
        val type = src.fromJson<CoordinateSystemData>().type

        builders.forEach { (key, value) ->
            if (type == key.simpleName) {
                return value.createInstance().build(src)
            }
        }

        throw IllegalArgumentException("$type isn't allowed or supported")
    }
}