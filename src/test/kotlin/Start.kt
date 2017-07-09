import rainbow.coordinate.system.cartesian.CartesianCoordinateSystem

fun main(args: Array<String>) {
    MainFrame(CartesianCoordinateSystem(2).apply {
        move(200, 200)
        rotate(1)
    })
}