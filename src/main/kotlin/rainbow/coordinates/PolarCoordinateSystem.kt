package rainbow.coordinates

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import rainbow.component.CoordinateTransformComponent
import rainbow.component.InputListenComponent
import rainbow.point.CoordinatePoint
import rainbow.point.Point2D
import rainbow.point.Point2DPolar
import rainbow.point.PointAxes.Companion.ZERO
import rainbow.utils.CoordinateGraphics
import rainbow.utils.PI2
import rainbow.utils.asPoint2D

/**
 * 二维的极坐标系
 *
 * @author Rainbow Yang
 */
class PolarCoordinateSystem : CoordinateSystem2D() {

    @Expose @SerializedName(typeName) override var type = this::class.simpleName!!

    @Expose override var x = 0.0
    @Expose override var y = 0.0

    @Expose @SerializedName(rotatedAngleName) override var rotatedAngle = 0.0
    @Expose @SerializedName(zoomRateName) override var zoomRate = 1.0

    //单位长度的像素数
    @Expose @SerializedName("Axis Length") var axisLength = 40.0


    @Expose @SerializedName(inputName) override var inputComponent: InputListenComponent
            = InputListenComponentOfCoordinateSystem2D(this)
    @Expose @SerializedName(paintName) override var paintComponent: PaintComponentOfCoordinateSystem
            = PainterForPolarCoordinateSystem(this)

    override var coordinateTransformComponent: CoordinateTransformComponent
            = CoordinateTransformComponentForPolarCoordinateSystem(this)

    class CoordinateTransformComponentForPolarCoordinateSystem(val system: PolarCoordinateSystem)
        : CoordinateTransformComponent() {

        override fun toScreenPoint(cp: CoordinatePoint) = with(system) {
            (cp.asPoint2D * system.axisLength).asPoint2D.rotateAndScaleAndMove()
        }


        override fun toCoordinatePoint(pd: Point2D) = with(system) {
            (pd.inverseRotateAndScaleAndMove() / axisLength).asAxes
        }
    }

    class PainterForPolarCoordinateSystem(system: PolarCoordinateSystem) : PaintComponentOfCoordinateSystem(system) {

        var paintRange = 0..30
        var axisNum = 12

        init {
            addPaintPart(ORIGIN) { paintOrigin(it) }
            addPaintPart(GRID) { paintGrid(it) }
            addPaintPart(AXES) { paintAxes(it) }
            addPaintPart(NUMBER) { paintNumber(it) }
        }

        fun paintOrigin(cg: CoordinateGraphics) {
            cg.paintString("0")
        }

        fun paintGrid(cg: CoordinateGraphics) {
            paintRange.forEach {
                cg.paintCircle(ZERO, Point2DPolar(it, 0))
            }
        }

        fun paintAxes(cg: CoordinateGraphics) {
            (0..axisNum).forEach {
                cg.paintRayLine(ZERO, Point2DPolar(10, PI2 / axisNum * it))
            }
        }

        fun paintNumber(cg: CoordinateGraphics) {
            paintRange.filter { it != 0 }.forEach {
                cg.paintString(it, ZERO.plusAtAndNew(0, it))
            }
        }
    }
}