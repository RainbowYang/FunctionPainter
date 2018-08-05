package rainbow.function.space.world

import jdk.nashorn.internal.objects.NativeFunction.function
import rainbow.function.CoordinateFunction
import rainbow.function.mathfunction.special.Grid
import rainbow.function.space.body.Body
import rainbow.point.Point3D
import rainbow.utils.CoordinateGraphics
import rainbow.utils.G
import rainbow.utils.asPoint3D
import java.awt.image.BufferedImage
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

    fun getGrid(): Grid {
        class WorldGrid() : Grid({ _, _ -> 0.0 }) {
            val world = this@World

            override fun getPaintedImage(width: Int, height: Int): BufferedImage {
                setRange(-20.0, 20.0, 0.1)

                val function: (Double, Double) -> Double = { x, y ->
                    val location = Point3D(x, y, 0)
                    var gravitySum = 0.0
                    bodies.forEach {
                        val length = (location - it.location).length

                        gravitySum -= it.mass / (length * length) * G

                    }
                    gravitySum

                }

                this@WorldGrid.functionsList.clear()

                for (j in -20..20) {
                    addFunctions(SELF, { j.toDouble() }, { function(it, j.toDouble()) })
                    addFunctions({ j.toDouble() }, SELF, { function(j.toDouble(), it) })
                }

                this@WorldGrid.cleanAllPoints()
                this@WorldGrid.calcPoints()

                return super.getPaintedImage(width, height)
            }


        }
        return WorldGrid()
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