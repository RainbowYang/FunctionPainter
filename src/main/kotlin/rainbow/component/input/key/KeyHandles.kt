package rainbow.component.input.key

/**
 * @author Rainbow Yang
 */
abstract class KeyHandles : KeyObserver {

    companion object {
        val Empty = object : KeyHandles() {}
    }

    val handles = mutableListOf<KeyHandle>()

    fun runHandle(key: Key, time: Number) = handles.forEach { it.runHandle(key, time) }

    protected operator fun Int.invoke(handle: (Int) -> Unit) {
        val key = Key(this)
        val keyHandle = KeyHandle(trigger = key, handle = handle)
        handles.add(keyHandle)
    }

    override fun registerTo(observable: KeyObservable) {
        observable.addHandles(this)
    }
}

