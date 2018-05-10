class Node(private val name: String?, private val label: String?,
           private val attrs: MutableMap<String, Any>) {

    override fun toString(): String {
        val sb = StringBuilder()
        if (!name.isNullOrEmpty())  sb.append(name)
        if (!label.isNullOrEmpty()) sb.append(":$label")
        if (!attrs.isEmpty()) {
            sb.append(" {")
            for ((k, v) in attrs) sb.append(" $k: ${if (v is String) "'$v'" else v},")
            sb[sb.length - 1] = ' '
            sb.append("} ")
        }
        return "($sb)"
    }
}
