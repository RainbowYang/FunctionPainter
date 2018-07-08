package rainbow.component.input.key

/**
 * 实现此接口之后应持有[KeyHandles],然后将其[registerTo]
 *
 * @author Rainbow Yang
 */
interface KeyHandlesOwner {
    /**
     * 将[KeyHandle]注册到[KeyInputSender]
     */
    fun registerTo(observable: KeyInputSender)
}