package QueryObjects

/**
 * QueryObjects.Query relationship
 *
 * Represents relationship itself without nodes
 * in Cypher style: "[name:type *minLen..maxLen { attr1: val1, attr2: val2, .. } ]"
 */
class Relationship(private val name: String?, private val type: String?,
                   private val attrs: MutableMap<String, Any>) {
    fun copy() = Relationship(name, type, attrs)

    private var pathLength = PathLength()
    fun setMinLength(len: Int) { pathLength.minLength = len }
    fun setMaxLength(len: Int) { pathLength.maxLength = len }

    override fun toString(): String {
        val sb = StringBuilder()
        if (!name.isNullOrEmpty()) sb.append(name)
        if (!type.isNullOrEmpty()) sb.append(":$type")
        if (pathLength.isNotDefault()) sb.append("*$pathLength")

        if (!attrs.isEmpty()) {
            sb.append(" {")
            for ((k, v) in attrs) sb.append(" $k: ${if (v is String) "'$v'" else v},")
            sb[sb.length - 1] = ' '
            sb.append("} ")
        }
        return if (!sb.isEmpty()) "[$sb]" else ""
    }

    /**
     * Util class for relationship length
     *
     * Default length bound is 1 both for min and max
     * Default length bounds don't need to be printed at all
     * Infinite bounds can be omitted with ".."
     */
    class PathLength {
        companion object Ranges {
            const val DEFAULT_PATH_LENGTH = 1
            const val ANY = -1 /* means infinite bound value */
        }
        var minLength = DEFAULT_PATH_LENGTH
        var maxLength = DEFAULT_PATH_LENGTH

        fun isNotDefault() = (minLength != DEFAULT_PATH_LENGTH || maxLength != DEFAULT_PATH_LENGTH)
        private fun isInfinite() = (minLength == ANY && maxLength == ANY)

        override fun toString(): String {
            val sb = StringBuilder()
            if (!isInfinite()) {
                if (minLength == maxLength) sb.append(minLength)
                else {
                    if (minLength != DEFAULT_PATH_LENGTH && minLength != ANY) sb.append(minLength)
                    sb.append("..")
                    if (maxLength != ANY) sb.append(maxLength)
                }
            }
            return "$sb"
        }
    }
}
