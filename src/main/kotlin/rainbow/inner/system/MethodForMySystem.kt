package rainbow.inner.system

import java.awt.Frame
import java.awt.Graphics

/**
 * MySystem及相关组件的扩展方法
 * @author Rainbow Yang
 */
fun MySystem.getIntWidth() = width.toInt()

fun MySystem.getIntHeight() = height.toInt()

fun MySystem.fillRect(g: Graphics) = g.fillRect(0, 0, getIntWidth(), getIntHeight())

fun MySystem.getAbout() = SystemAbout
fun MySystem.getInformation() = SystemInformation
fun MySystem.getColors() = SystemColors
fun MySystem.getListeners() = SystemListeners
fun MySystem.getPainters() = SystemPainters

fun MySystem.setSize(f: Frame) = f.setSize(getIntWidth(), getIntHeight())
fun MySystem.setTitle(f: Frame) = SystemAbout.setTitle(f)
fun SystemAbout.setTitle(f: Frame) {
    f.title = "${SystemAbout.name} ${SystemAbout.version}  Author:${SystemAbout.author}"
}
