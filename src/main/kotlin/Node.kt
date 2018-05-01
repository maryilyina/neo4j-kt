class Node(val name: String?, val label: String?,
           val attrs: MutableMap<String, Any>) {

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(name)
        if (label != null) sb.append(":$label")
        if (!attrs.isEmpty()) {
            sb.append(" {")
            for ((k, v) in attrs) sb.append(" $k: ${if (v is String) "'$v'" else v},")
            sb[sb.length - 1] = ' '
            sb.append("} ")
        }
        return "($sb)"
    }
}