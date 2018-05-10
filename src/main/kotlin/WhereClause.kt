/**
 * Where clause
 *
 * A list of conditions in string format to be satisfied in a statement
 */
class WhereClause(private val conditions: MutableList<String>) {
    override fun toString(): String {
        val sb = StringBuilder()
        for (condition in conditions)
            sb.appendln("WHERE $condition")
        return "$sb".trim()
    }
}

