class Relationship(private val name: String?, private val type: String?,
                   private val attrs: MutableMap<String, Any>) {
    companion object {
        const val DEFAULT_PATH_LENGTH = 1
        const val ANY = -1
    }
    var nodeFrom: Node? = null
    var nodeTo: Node? = null
    var isDirected = true
    var alias: String? = null

    var minHops = DEFAULT_PATH_LENGTH
    var maxHops = DEFAULT_PATH_LENGTH

    var backRelationship: Relationship? = null
    var backNodeFrom: Node? = null

    fun copy() = Relationship(name, type, attrs)

    private fun lengthCanBeOmitted() = (minHops == ANY && maxHops == ANY)
    private fun lengthIsSpecified() = (minHops != DEFAULT_PATH_LENGTH || maxHops != DEFAULT_PATH_LENGTH)

    fun stringDescription(): String {
        val sb = StringBuilder()
        if (!name.isNullOrEmpty()) sb.append(name)
        if (!type.isNullOrEmpty()) sb.append(":$type")

        if (lengthIsSpecified()) {
            sb.append('*')
            if (!lengthCanBeOmitted()) {
                if (minHops == maxHops) sb.append(minHops)
                else {
                    if (minHops != DEFAULT_PATH_LENGTH && minHops != ANY) sb.append(minHops)
                    sb.append("..")
                    if (maxHops != ANY) sb.append(maxHops)
                }
            }
        }

        if (!attrs.isEmpty()) {
            sb.append(" {")
            for ((k, v) in attrs) sb.append(" $k: ${if (v is String) "'$v'" else v},")
            sb[sb.length - 1] = ' '
            sb.append("} ")
        }
        return if (!sb.isEmpty()) "[$sb]" else ""
    }

    override fun toString(): String {
        val strRel = stringDescription()
        val rel = if (isDirected) "-$strRel->" else "-$strRel-"

        val pathAlias = if (!alias.isNullOrEmpty()) "$alias=" else ""
        val backRel = if (backRelationship != null && backNodeFrom != null)
            "${backRelationship?.stringDescription()}<-$backNodeFrom" else ""

        return "$pathAlias$nodeFrom$rel$nodeTo$backRel"
    }

}