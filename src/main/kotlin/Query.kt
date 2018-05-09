class Query {
    private val statements = mutableListOf<Statement>()
    var returnValue: String? = null

    fun append(statement: Statement) = this.also { statements.add(statement) }

    fun toString(lastStatement: Statement): String {
        val sb = StringBuilder()
        for (st in statements + lastStatement) sb.appendln(st.stringify())
        if (!returnValue.isNullOrBlank()) sb.appendln("RETURN $returnValue")
        return "$sb".trim()
    }
}
