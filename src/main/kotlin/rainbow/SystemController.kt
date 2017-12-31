package rainbow

import rainbow.component.input.key.KeyObservable
import rainbow.coordinates.CoordinateSystem
import rainbow.function.CoordinateFunction
import rainbow.utils.buildJFrame
import rainbow.utils.drawImageOfPainter
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JFrame
import javax.swing.JPanel
import kotlin.concurrent.schedule

/**
 * @author Rainbow Yang
 */
class SystemController(extraInit: SystemController.() -> Unit) {

    var width: Number = 1000
    var height: Number = 500

    /**
     * 刷新周期
     */
    var period: Int = 10

    var task: () -> Unit = {}

    lateinit var coordinateSystem: CoordinateSystem

    var functionList = mutableListOf<CoordinateFunction>()

    private lateinit var frame: JFrame

    val keyObservable = KeyObservable()

    init {
        extraInit()

        run()
    }

    fun <S : CoordinateSystem> setCoordinateSystem(coordinateSystem: S, init: S.() -> Unit) {
        coordinateSystem.init()
        this.coordinateSystem = coordinateSystem
        coordinateSystem.registerTo(keyObservable)
    }

    fun addFunction(function: CoordinateFunction) = functionList.add(function)


    fun run() {
        initForFunctions()
        initForFrame()
        initForkeyObservable()
        initForRepaintTimer()
    }

    fun initForFunctions() {
        functionList.forEach {
            it.coordinateSystem = coordinateSystem
            it.init()
        }
    }

    fun initForFrame() {
        frame = buildJFrame(width, height).apply {
            title = "FunctionPainter2 By Rainbow_Yang"
            background = Color.BLACK
            add(object : JPanel() {
                override fun paintComponent(g: Graphics) {
                    g as Graphics2D
                    g.drawImageOfPainter(coordinateSystem, this.size)
                    functionList.forEach { g.drawImageOfPainter(it, this.size) }
                }
            })
        }
    }


    fun initForkeyObservable() {
        frame.addKeyListener(keyObservable.getListener())
        keyObservable.startToRunHandles()
    }


    fun initForRepaintTimer() {
        RepaintTimer().run()
    }

    inner class RepaintTimer {
        fun run() {
            java.util.Timer().schedule(0, period.toLong()) {
                task()
                frame.repaint()
            }
        }
    }
}