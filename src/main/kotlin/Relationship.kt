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
        if (pathLength.isSpecified()) sb.append("*$pathLength")

        if (!attrs.isEmpty()) {
            sb.append(" {")
            for ((k, v) in attrs) sb.append(" $k: ${if (v is String) "'$v'" else v},")
            sb[sb.length - 1] = ' '
            sb.append("} ")
        }
        return if (!sb.isEmpty()) "[$sb]" else ""
    }

    class PathLength {
        companion object Ranges {
            const val DEFAULT_PATH_LENGTH = 1
            const val ANY = -1
        }
        var minLength = DEFAULT_PATH_LENGTH
        var maxLength = DEFAULT_PATH_LENGTH

        override fun toString(): String {
            val sb = StringBuilder()
            if (!canBeOmitted()) {
                if (minLength == maxLength) sb.append(minLength)
                else {
                    if (minLength != DEFAULT_PATH_LENGTH && minLength != ANY) sb.append(minLength)
                    sb.append("..")
                    if (maxLength != ANY) sb.append(maxLength)
                }
            }
            return "$sb"
        }

        private fun canBeOmitted() = (minLength == ANY && maxLength == ANY)
        fun isSpecified() = (minLength != DEFAULT_PATH_LENGTH || maxLength != DEFAULT_PATH_LENGTH)
    }
}
