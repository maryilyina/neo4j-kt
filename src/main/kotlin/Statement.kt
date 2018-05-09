abstract class Statement(private val nodes: MutableList<Node>,
                         private val patterns: MutableList<Pattern>) {
    abstract val statementName: String
    var baseQuery = Query()

    override fun toString() = baseQuery.toString(this)

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

        return "$statementName $sb"
    }
}

operator fun Statement.plus(other: Statement) = other.also { it.baseQuery = baseQuery.append(this) }

infix fun Statement.returns(what: String) : Statement = this.apply { baseQuery.returnValue = what }

