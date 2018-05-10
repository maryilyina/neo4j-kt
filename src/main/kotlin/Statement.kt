abstract class Statement(private val nodes: MutableList<Node>,
                         private val patterns: MutableList<Pattern>) {
    abstract val statementName: String
    var baseQuery = Query()
    var whereClause: WhereClause? = null

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
        if (whereClause != null) sb.append("\n$whereClause")
        return "$statementName $sb"
    }
}

/* Combining multiple clauses in one query*/
operator fun Statement.plus(other: Statement) = other.also { it.baseQuery = baseQuery.append(this) }
infix fun Statement.and(other: Statement) = this + other

/* Additional functions */
infix fun Statement.returns(what: String) : Statement = this.apply { baseQuery.setReturnValue(what) }

infix fun Statement.limit(howMany: Any) : Statement = this.apply { baseQuery.setReturnLimit(howMany) }

infix fun Statement.orderBy(how: String) : Statement = this.apply { baseQuery.setOrderStyle(how) }
