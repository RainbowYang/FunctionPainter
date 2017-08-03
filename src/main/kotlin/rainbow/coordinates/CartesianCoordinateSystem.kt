package rainbow.coordinates

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import rainbow.component.CoordinateTransformComponent
import rainbow.component.InputListenComponent
import java.lang.Math.*

/**
 * 任意维度的轴坐标系
 *
 * 但由于其任意纬度的特性，可能不会有专门的立体效果
 * 故继承[CoordinateSystem2D]
 *
 * @author Rainbow Yang
 * @see CoordinateSystem2D
 */
class CartesianCoordinateSystem(size: Int = 3,
                                val paintAsBall: Boolean = true,
                                init: CartesianCoordinateSystem.() -> Unit = { setDefaultAxes(size) }
) : CoordinateSystem2D() {
    @Expose @SerializedName(typeName) override var type = this::class.simpleName!!
    @Expose override var x = 0.0
    @Expose override var y = 0.0
    @Expose @SerializedName(rotatedAngleName) override var rotatedAngle = 0.0
    @Expose @SerializedName(zoomRateName) override var zoomRate = 1.0
    @Expose @SerializedName(inputName) override var inputComponent: InputListenComponent
            = InputListenComponentOfCartesianCoordinateSystem(this)
    override var coordinateTransformComponent: CoordinateTransformComponent
            = CoordinateTransformComponentOfCartesianCoordinateSystem(this)
    override var paintComponent: PaintComponentOfCoordinateSystem
            = PaintComponentOfCartesianCoordinateSystem(this)

    init {
        init()

        axes.forEach { }
    }


    lateinit var axes: MutableList<CartesianCoordinateSystemAxis>

    val size: Int get() = axes.size


    fun setDefaultAxes(size: Int) {
        axes = mutableListOf<CartesianCoordinateSystemAxis>()

        when (size) {
            3 -> if (paintAsBall) addAxes(0 to 0, 90 to 0, 0 to 90) else addAxes(225, 0, 90)
            2 -> if (paintAsBall) addAxes(90 to 0, 0 to 90) else addAxes(0, 90)
        }
    }

    fun addAxes(vararg axis: Pair<Number, Number>) = axis.forEach { axes.add(BallAxis(it.first, it.second)) }
    fun addAxes(vararg angles: Number) = angles.forEach { axes.add(ClassicAxis(toRadians(it.toDouble()))) }

    override fun toString(): String {
        return "CartesianCoordinateSystem(axes=$axes, paintAsBall=$paintAsBall)"
    }


}