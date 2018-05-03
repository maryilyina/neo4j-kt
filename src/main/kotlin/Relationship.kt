class Relationship(val name: String?, val type: String?,
               val attrs: MutableMap<String, Any>) {
    var nodeFrom: Node? = null
    var nodeTo: Node? = null

    override fun toString(): String {
        val sb = StringBuilder()
        if (!name.isNullOrEmpty()) sb.append(name)
        if (!type.isNullOrEmpty()) sb.append(":$type")
        if (!attrs.isEmpty()) {
            sb.append(" {")
            for ((k, v) in attrs) sb.append(" $k: ${if (v is String) "'$v'" else v},")
            sb[sb.length - 1] = ' '
            sb.append("} ")
        }
        val rel = if (!sb.isEmpty()) "[$sb]" else ""
        return "$nodeFrom-$rel->$nodeTo"

    }
}