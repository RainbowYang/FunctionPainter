package rainbow.component.input

/**
 * 实现此接口表示其可以接受来自键盘的输入
 *
 * 但逻辑应通过组合[KeyController]来实现
 *
 * @author Rainbow Yang
 */
interface KeyControllable {
    fun setKey(keyMap: KeyMap)
}