abstract class Statement(private val nodes: MutableList<Node>,
                         private val patterns: MutableList<Pattern>) {
    abstract val statementName: String
    var queryText: String = ""
    var returnValue: String? = null

    override fun toString() = "$queryText\n${stringify()}".trim()

    fun stringify(): String {
        if (nodes.isEmpty() && patterns.isEmpty()) return ""
        val sb = StringBuilder()

        if (nodes.isNotEmpty()) {
            for (node in nodes) sb.append("$node,")
            sb[sb.length - 1] = ' '
        }

        if (patterns.isNotEmpty()) {
            for (rel in patterns) sb.append("$rel,")
            sb[sb.length - 1] = ' '
        }

        if (!returnValue.isNullOrBlank()) sb.append("\nRETURN $returnValue")
        return "$statementName $sb"
    }
}

operator fun Statement.plus(other: Statement) = other.also { it.queryText += "$queryText\n${stringify()}" }

infix fun Statement.returns(what: String) : Statement = this.apply { returnValue = what }

