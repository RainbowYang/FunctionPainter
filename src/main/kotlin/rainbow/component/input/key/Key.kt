package rainbow.component.input.key

/**
 * 此类用来表示一种键盘输入方式
 * @author Rainbow Yang
 */
class Key(val key: Int,
          val hasAlt: Boolean = false,
          val hasCtrl: Boolean = false,
          val hasShift: Boolean = false
) {

    override fun toString(): String {
        return "Key(key=$key, hasAlt=$hasAlt, hasCtrl=$hasCtrl, hasShift=$hasShift)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Key

        if (key != other.key) return false
        if (hasAlt != other.hasAlt) return false
        if (hasCtrl != other.hasCtrl) return false
        if (hasShift != other.hasShift) return false

        return true
    }

}