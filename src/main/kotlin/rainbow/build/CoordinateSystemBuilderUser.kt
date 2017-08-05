package rainbow.build

import rainbow.coordinates.CartesianCoordinateSystemBall
import rainbow.coordinates.CartesianCoordinateSystemClassic
import rainbow.coordinates.CoordinateSystem
import rainbow.coordinates.PolarCoordinateSystem
import rainbow.utils.fromJson
import rainbow.utils.toJson
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance


/**
 * 坐标系构建器
 *
 * @author Rainbow Yang
 */
object CoordinateSystemBuilderUser {

    val builders: HashMap<KClass<out CoordinateSystem>, KClass<out CoordinateSystemBuilder>> = hashMapOf()

    init {
        builders.put(PolarCoordinateSystem::class, PolarCoordinateSystemBuilder::class)
        builders.put(CartesianCoordinateSystemBall::class, CartesianCoordinateSystemBallBuilder::class)
        builders.put(CartesianCoordinateSystemClassic::class, CartesianCoordinateSystemClassicBuilder::class)
    }

    fun build(json: String) = findBuilder(json.fromJson<CoordinateSystemData>().type).build(json)

    private fun findBuilder(type: String?): CoordinateSystemBuilder {
        builders.forEach { (key, value) ->
            if (type == key.simpleName) {
                return value.createInstance()
            }
        }

        throw IllegalArgumentException("$type isn't allowed or supported")
    }

}