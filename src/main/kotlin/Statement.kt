/**
 * Basic unit of query
 *
 * Consists of described patterns and individual nodes
 * Represented as:
 *          "STATEMENT_NAME node1, node2, .., pattern1, pattern2, ..
 *           WHERE whereClause
 *           RETURN returnValue
 *           ORDER BY orderStyle
 *           LIMIT returnLimit"
 *
 *  Base query stores all statements already described, except this one
 */
abstract class Statement(private val nodes: MutableList<Node>,
                         private val patterns: MutableList<Pattern>) {
    abstract val statementName: String
    private var baseQuery = Query()

    private var whereClause: WhereClause? = null
    private var returnValue: String? = null
    private var orderStyle: String? = null
    private var returnLimit: Any? = null

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
        sb.appendln()
        if (whereClause != null) sb.appendln("$whereClause")
        if (!returnValue.isNullOrBlank()) sb.appendln("RETURN $returnValue")
        if (!orderStyle.isNullOrBlank())  sb.appendln("ORDER BY $orderStyle")
        if (returnLimit != null)          sb.appendln("LIMIT $returnLimit")
        return "$statementName ${sb.trim()}"
    }

    fun setWhereClause(clause: WhereClause) { whereClause = clause }
    fun setReturnValue(s: String)  { returnValue = s }
    fun setOrderStyle(s: String)   { orderStyle = s }
    fun setReturnLimit(s: Any)     { returnLimit = s }

    fun setBaseQuery(query: Query) { baseQuery = query }
    /* Enlarges baseQuery by current statement and returns it */
    fun getFullQuery() = baseQuery.append(this)
}

/* Combining multiple clauses in one query*/
operator fun Statement.plus(other: Statement) = other.also { it.setBaseQuery(getFullQuery()) }
infix fun Statement.and(other: Statement) = this + other

/* Additional functions */
infix fun Statement.returns(what: String) : Statement = this.apply { setReturnValue(what) }

infix fun Statement.limit(howMany: Any) : Statement = this.apply { setReturnLimit(howMany) }

infix fun Statement.orderBy(how: String) : Statement = this.apply { setOrderStyle(how) }
