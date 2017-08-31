package rainbow

import rainbow.coordinates.CoordinateSystem
import rainbow.function.CoordinateFunction
import rainbow.input.KeyMap
import rainbow.utils.buildJFrame
import rainbow.utils.drawImageOfPainter
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame
import javax.swing.JPanel
import kotlin.concurrent.schedule

/**
 * 此类负责各方面的联系
 * @author Rainbow Yang
 */
class MainSystem(init: MainSystem.() -> Unit) {

    var width: Number = 1000
    var height: Number = 500

    var fps: Number = 60
    var period: Number
        get() = 1000.0 / fps.toDouble()
        set(value) {
            fps = 1000.0 / value.toDouble()
        }

    var task: () -> Unit = {}

    lateinit var coordinateSystem: CoordinateSystem

    var functionList = mutableListOf<CoordinateFunction>()

    private lateinit var frame: JFrame

    val keyMap = KeyMap()

    init {
        init()
    }

    fun <S : CoordinateSystem> setCoordinateSystem(coordinateSystem: S, init: S.() -> Unit) {
        coordinateSystem.init()
        this.coordinateSystem = coordinateSystem
        coordinateSystem.addKeyHandlesTo(keyMap)
    }

    fun addFunction(function: CoordinateFunction) = functionList.add(function)

    fun initForFunctions() {
        functionList.forEach {
            it.init()
            it.coordinateSystem = coordinateSystem
        }
    }

    fun run() {

        initForFunctions()

        frame = buildJFrame(width, height).apply {
            add(object : JPanel() {
                override fun paintComponent(g: Graphics) {
                    g as Graphics2D
                    g.drawImageOfPainter(coordinateSystem, width, height)
                    functionList.forEach { g.drawImageOfPainter(it, width, height) }
                }
            })
            addKeyListener(keyMap.getListener())
        }

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