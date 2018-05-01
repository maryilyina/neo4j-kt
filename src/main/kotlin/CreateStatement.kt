class CreateStatement(val nodes: MutableList<Node>) {
    override fun toString(): String {
        if (nodes.isEmpty()) return ""
        val sb = StringBuilder()
        for (node in nodes) sb.append("$node,")
        sb[sb.length - 1] = ' '
        return "CREATE $sb"
    }
}