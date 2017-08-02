package rainbow.function.space.world

import rainbow.function.CoordinateFunction
import rainbow.function.space.body.Body
import rainbow.function.space.point.Point3D
import rainbow.utils.CoordinateGraphics
import java.util.*
import kotlin.concurrent.schedule

/**
 * [Body]所运行的空间
 *
 * 运行顺序：
 * 位置=>引力=>加速度=>速度=>位移
 *
 * @author Rainbow Yang
 */
class World : CoordinateFunction() {

    override var paintComponent: CoordinateFunctionPainter = WorldPainter(this)

    var fps: Double = 60.0

    //重新计算的现实时间间隔
    var timeSpace: Double
        get() = 1 / fps
        set(value) {
            fps = 1 / timeSpace
        }

    //运行速度与现实速度的比
    var timeRate: Double = 1.0

    val time get() = timeSpace * timeRate

    val bodies: MutableList<Body> = mutableListOf()

    fun addBody(body: Body) = bodies.add(body)

    override fun init() {
        run()
    }

    fun run() {
        Timer().schedule(0L, (timeSpace * 1000).toLong()) {
            timeBy()
        }
    }


    private fun timeBy() {
        bodies.forEach { now ->
            var gravitySum = Point3D.ZERO
            bodies.forEach {
                if (it !== now) {
                    gravitySum += (now gravityFrom it)
                }
            }
            now.velocity += (gravitySum / now.mass) * time
            now.location += now.velocity * time
        }
    }

    class WorldPainter(val world: World) : CoordinateFunctionPainter() {
        override fun paintBefore(cg: CoordinateGraphics) {
            super.paintBefore(cg)
        }

        override fun paintMain(cg: CoordinateGraphics) {
            super.paintMain(cg)
        }

        override fun paintAfter(cg: CoordinateGraphics) {
            super.paintAfter(cg)
        }
    }
}