package rainbow

import rainbow.coordinates.CartesianCoordinateSystem4D
import rainbow.function.Parallelotope
import rainbow.point.Point3D
import rainbow.point.PointAxes


fun main(args: Array<String>) {

    MainSystem {
        fps = 60

//        task = {
//            (coordinateSystem as CoordinateSystem2D).rotate(- R.001)
//        }

        setCoordinateSystem(CartesianCoordinateSystem4D()) {

            camera = Point3D(-5, 0, 10)

            //            painter.visible = false
        }
//        addFunction(RegularPolygon(100, 10.0, 60))
//        addFunction(RegularPolygon(6, 10.- R, 2))
//        addFunction(Lissajous(3, 4, 5))

        addFunction(Parallelotope(
                PointAxes()
                , PointAxes(5)
                , PointAxes(0, 5)
                , PointAxes(0, 0, 5)
                , PointAxes(0, 0, 0, 20)
//                , PointAxes(0, 0, 0, 0, 50)
        ))

    }
}