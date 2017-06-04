package rainbow

import rainbow.inner.coordinate.system.CoordinateSystemForAxes
import rainbow.inner.function.mathfunction.simple._2D.ExpFunction
import rainbow.inner.function.mathfunction.simple._2D.LogFunction
import rainbow.inner.function.mathfunction.simple._2D.PowerFunction
import rainbow.inner.function.mathfunction.simple._2D.spiral.*
import rainbow.inner.function.mathfunction.simple._2D.trigonometric_function.Sin
import rainbow.inner.function.mathfunction.simple._2D.trigonometric_function.TrigonometricFunction
import rainbow.inner.function.mathfunction.simple._2D.trigonometric_function.TrigonometricFunction.*
import rainbow.inner.function.mathfunction.simple._3D.Ellipsoid
import rainbow.inner.function.mathfunction.special.Lissajous
import rainbow.inner.function.mathfunction.special.Net
import rainbow.inner.function.mathfunction.special._2D.conic_section.ConicSection
import rainbow.inner.function.mathfunction.special._2D.cycloid.*
import rainbow.inner.function.pointfunction.Hypercube
import rainbow.inner.function.pointfunction.RegularPolygon
import rainbow.inner.system.MySystem
import rainbow.outer.frame.MainFrame
import rainbow.tools.CodeReader

/**
 * 这是FunctionPainter2，一个重新开始的版本
 * 没错，之前的版本最终被我放弃了
 * @author Rainbow Yang
 */

fun main(args: Array<String>) {
    readCode()
    systemInit()
    functionsInit()
    showFrame()
}

fun systemInit() {
    MySystem.getSystem().coordinateSystem = CoordinateSystemForAxes(4)
}

fun functionsInit() {
    val functions = MySystem.getSystem().coordinateSystem.functions

    functions.add(ExpFunction(1.0, Math.E))
    functions.add(LogFunction(1.0, Math.E))
    functions.add(PowerFunction("1*x^2"))

    functions.add(TrigonometricFunction(1, 1, 0, MODE_SIN))
    functions.add(TrigonometricFunction(1, 1, 0, MODE_COS))
    functions.add(TrigonometricFunction(1, 1, 0, MODE_TAN))
    functions.add(TrigonometricFunction(1, 1, 0, MODE_COT))
    functions.add(TrigonometricFunction(1, 1, 0, MODE_SEC))
    functions.add(TrigonometricFunction(1, 1, 0, MODE_CSC))

    functions.add(ConicSection(5, 5, MODE_ELLIPSE_X))
    functions.add(ConicSection(5, 5, MODE_ELLIPSE_Y))
    functions.add(ConicSection(5, 5, MODE_HYPERBOLA_X))
    functions.add(ConicSection(5, 5, MODE_HYPERBOLA_Y))
    functions.add(ConicSection(5, MODE_PARABOLA_X))
    functions.add(ConicSection(5, MODE_PARABOLA_Y))

    functions.add(Ellipsoid(6.0, 6.0, 6.0))
    functions.add(Lissajous(13, 18, 27))
    functions.add(RegularPolygon(6, 5.0, 2))

    functions.add(FermatSpiral(100.0, 1.0))
    functions.add(IsometricSpiral(1.0, 0.5))
    functions.add(HyperbolicSpiral(1.0))
    functions.add(ArchimedeanSpiral(1.0))
    functions.add(LituusSpiral(1.0))

    functions.add(Cycloid())
    functions.add(Hypotrochoid(10.0, 4.0, 1.0))
    functions.add(Hypocycloid(10.0, 4.0))
    functions.add(Epitrochoid(6.0, 4.0, 4.0))
    functions.add(Epicycloid(10.0, 3.0))

    functions.add(Net { x: Double, y: Double -> -10 / Math.sqrt(0.000001 + (x * x) + (y * y)) })

    functions.add(Hypercube(10.0, 4))

    functions.clacFunctions()
}

fun showFrame() {
    MainFrame()
}

fun readCode() {
    CodeReader(".//src//rainbow").print(CodeReader.DETAILED)
}