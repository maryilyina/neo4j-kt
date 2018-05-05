class Relationship(val name: String?, val type: String?,
               val attrs: MutableMap<String, Any>) {
    companion object {
        const val DEFAULT_PATH_LENGTH = 1
        const val INF = Int.MAX_VALUE
    }
    var nodeFrom: Node? = null
    var nodeTo: Node? = null
    var pathLengthMin = DEFAULT_PATH_LENGTH
    var pathLengthMax = DEFAULT_PATH_LENGTH

    override fun toString(): String {
        val sb = StringBuilder()
        if (!name.isNullOrEmpty()) sb.append(name)
        if (!type.isNullOrEmpty()) sb.append(":$type")

        if (lengthIsSpecified()) {
            sb.append('*')
            if (lengthIsFinite()) {
                if (pathLengthMin == pathLengthMax) sb.append(pathLengthMin)
                else {
                    if (pathLengthMin != 0) sb.append(pathLengthMin)
                    sb.append("..")
                    if (pathLengthMax != INF) sb.append(pathLengthMax)
                }
            }
        }

    if (!attrs.isEmpty()) {
            sb.append(" {")
            for ((k, v) in attrs) sb.append(" $k: ${if (v is String) "'$v'" else v},")
            sb[sb.length - 1] = ' '
            sb.append("} ")
        }
        val rel = if (!sb.isEmpty()) "[$sb]" else ""
        return "$nodeFrom-$rel->$nodeTo"

    }

    fun copy() = Relationship(name, type, attrs)

    private fun lengthIsFinite() = (pathLengthMin != 0 || pathLengthMax != INF)
    private fun lengthIsSpecified() = (pathLengthMin != DEFAULT_PATH_LENGTH || pathLengthMax != DEFAULT_PATH_LENGTH)
}