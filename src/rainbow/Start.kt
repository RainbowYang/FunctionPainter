package rainbow

import rainbow.inner.coordinate.system.CoordinateSystemForAxes
import rainbow.inner.function.mathfunction.special.Net
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

    // functions.add( ExpFunction(1, Math.E));
    // functions.add( LogFunction(1, Math.E));
    // functions.add( PowerFunction("1*x^2"));
    //
    // functions.add( TrigonometricFunction(1, 1, 0, MODE_SIN));
    // functions.add( TrigonometricFunction(1, 1, 0, MODE_COS));
    // functions.add( TrigonometricFunction(1, 1, 0, MODE_TAN));
    // functions.add( TrigonometricFunction(1, 1, 0, MODE_COT));
    // functions.add( TrigonometricFunction(1, 1, 0, MODE_SEC));
    // functions.add( TrigonometricFunction(1, 1, 0, MODE_CSC));
    //
    // functions.add( ConicSection(5, 5, MODE_ELLIPSE_X));
    // functions.add( ConicSection(5, 5, MODE_ELLIPSE_Y));
    // functions.add( ConicSection(5, 5, MODE_HYPERBOLA_X));
    // functions.add( ConicSection(5, 5, MODE_HYPERBOLA_Y));
    // functions.add( ConicSection(5, MODE_PARABOLA_X));
    // functions.add( ConicSection(5, MODE_PARABOLA_Y));
    //
    // functions.add( Ellipsoid(6, 6, 6));
    // functions.add( Lissajous(13, 18, 27));
    // functions.add( RegularPolygon(6, 5, 2));
    //
    // functions.add( FermatSpiral(100, 1));
    // functions.add( IsometricSpiral(1, 0.5));
    // functions.add( HyperbolicSpiral(1));
    // functions.add( ArchimedeanSpiral(1));
    // functions.add( LituusSpiral(1));
    //
    // functions.add( Cycloid());
    // functions.add( Hypotrochoid(10, 4, 1));
    // functions.add( Hypocycloid(10, 4));
    // functions.add( Epitrochoid(6, 4, 4));
    // functions.add( Epicycloid(10, 3));

    functions.add(Net { x: Double, y: Double -> -10 / Math.sqrt(0.000001 + (x * x) + (y * y)) })

    // functions.add(new Hypercube(10, 4));

    functions.clacFunctions()
}

fun showFrame() {
    MainFrame()
}

fun readCode() {
    CodeReader(".//src//rainbow").print(CodeReader.DETAILED)
}