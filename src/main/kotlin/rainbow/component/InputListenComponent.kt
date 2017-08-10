package rainbow.component

import java.awt.Component
import java.awt.event.*

/**
 * 用于接受鼠标和键盘的操作的组件
 *
 * @author Rainbow Yang
 */
abstract class InputListenComponent : MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

    /**
     *  被添加了此类的[Component]
     */
    lateinit var component: Component

    //TODO callback 在绘画完毕之后回调 可能取消 使用定时绘画机制
    protected fun repaint() = component.repaint()

    /**
     * 为[Component]添加此类的监听
     */
    fun bindTo(component: Component) {
        this.component = component

        component.addKeyListener(this)
        component.addMouseListener(this)
        component.addMouseMotionListener(this)
        component.addMouseWheelListener(this)
    }

    override fun mouseReleased(e: MouseEvent) {}
    override fun mouseEntered(e: MouseEvent) {}
    override fun mouseClicked(e: MouseEvent) {}
    override fun mouseExited(e: MouseEvent) {}
    override fun mousePressed(e: MouseEvent) {}

    override fun mouseMoved(e: MouseEvent) {}
    override fun mouseDragged(e: MouseEvent) {}

    override fun mouseWheelMoved(e: MouseWheelEvent) {}

    override fun keyTyped(e: KeyEvent) {}
    override fun keyPressed(e: KeyEvent) {}
    override fun keyReleased(e: KeyEvent) {}

}