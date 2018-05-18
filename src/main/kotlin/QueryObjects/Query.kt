package QueryObjects

/**
 * QueryObjects.Query in Cypher language
 *
 * Represents a list of described statements to be executed together
 */
class Query {
    private val statements = mutableListOf<Statement>()
    fun append(statement: Statement) = this.also { statements.add(statement) }

    fun toString(lastStatement: Statement): String {
        val sb = StringBuilder()
        for (st in statements + lastStatement)
            sb.appendln(st.stringify())
        return "$sb".trim()
    }
}
