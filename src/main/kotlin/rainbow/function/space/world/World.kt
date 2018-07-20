package rainbow.function.space.world

import rainbow.function.CoordinateFunction
import rainbow.function.space.body.Body
import rainbow.point.Point3D
import rainbow.utils.CoordinateGraphics
import rainbow.utils.asPoint3D
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

    override var painter: Painter = WorldPainter()

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
                    gravitySum = (gravitySum + (now gravityFrom it)).asPoint3D
                }
            }
            now.velocity = (now.velocity + (gravitySum / now.mass) * time).asPoint3D
            now.move(time)
        }
    }

    inner class WorldPainter : CoordinateFunction.Painter() {
        override fun paintMain(cg: CoordinateGraphics) {
            bodies.forEach {
                it.selfPaint(cg)
            }
        }
    }
}