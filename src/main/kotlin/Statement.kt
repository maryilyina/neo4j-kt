abstract class Statement(private val nodes: MutableList<Node>,
                         private val relationships: MutableList<Relationship>) {
    abstract val statementName: String

    var returnValue: String? = null

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

        if (!returnValue.isNullOrBlank()) sb.append(" RETURN $returnValue")
        return "$statementName $sb"
    }
}