class CreateStatement(val nodes: MutableList<Node>, val relationships: MutableList<Relationship>) {
    var ret_val: String? = null

    override fun toString(): String {
        if (nodes.isEmpty() && relationships.isEmpty()) return ""
        val sb = StringBuilder()

        if (nodes.isNotEmpty()) {
            for (node in nodes) sb.append("$node,")
            sb[sb.length - 1] = ' '
        }

        if (relationships.isNotEmpty()) {
            for (rel in relationships) sb.append("$rel,")
            sb[sb.length - 1] = ' '
        }

        if (!ret_val.isNullOrBlank()) sb.append(" RETURN $ret_val")
        return "CREATE $sb"
    }
}