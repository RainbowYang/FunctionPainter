package rainbow.coordinate.function

/**
 * 本类用于存储[CoordinateFunction]
 * @author Rainbow Yang
 */
class CoordinateFunctions(var functions: MutableList<CoordinateFunction> = mutableListOf()) {
    companion object {
        operator fun invoke(vararg initFunctions: CoordinateFunction) {
            CoordinateFunctions(initFunctions.toMutableList())
        }
    }

    inline fun forEach(init: (CoordinateFunction) -> Unit) = functions.forEach(init)
}