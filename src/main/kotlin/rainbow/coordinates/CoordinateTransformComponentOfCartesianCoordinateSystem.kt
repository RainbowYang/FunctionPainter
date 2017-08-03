package rainbow.coordinates

import rainbow.component.CoordinateTransformComponent
import rainbow.point.CoordinatePoint
import rainbow.point.Point2D
import rainbow.point.PointAxes

open class CoordinateTransformComponentOfCartesianCoordinateSystem(val system: CartesianCoordinateSystem) :
        CoordinateTransformComponent() {
    override fun toScreenPoint(cp: CoordinatePoint): Point2D = with(system) {
        val form = cp.asAxes
        var px = 0.0
        var py = 0.0
        for (i in 0..axes.size - 1) {
            val (angle, length) = axes[i]
            px += Math.cos(angle) * length * form[i]
            py += Math.sin(angle) * length * form[i]
        }
        return Point2D(px, py).rotateAndScaleAndMove()
    }


    override fun toCoordinatePoint(p: Point2D): CoordinatePoint = with(system) {
        val pd = p.inverseRotateAndScaleAndMove()

        val x = pd.spin(Math.PI / 2 - axes[1].angle).x
        val y = pd.spin(0 - axes[0].angle).y

        val xAngle = axes[0].angle + Math.PI / 2 - axes[1].angle
        val yAngle = axes[1].angle - axes[0].angle

        val px = x / Math.cos(xAngle) / axes[0].length
        val py = y / Math.sin(yAngle) / axes[1].length

        return PointAxes(px, py)

    }
}

