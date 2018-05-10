class CreateStatementBuilder : StatementBuilder() {
    fun build() = CreateStatement(nodes, patterns)
}
fun create(block: CreateStatementBuilder.() -> Unit) = CreateStatementBuilder().apply(block).build()


class MatchStatementBuilder : StatementBuilder() {
    fun build() = MatchStatement(nodes, patterns)
}
fun match(block: MatchStatementBuilder.() -> Unit) = MatchStatementBuilder().apply(block).build()


class MergeStatementBuilder : StatementBuilder() {
    fun build() = MergeStatement(nodes, patterns)
}
fun merge(block: MergeStatementBuilder.() -> Unit) = MergeStatementBuilder().apply(block).build()


class OptionalMatchStatementBuilder : StatementBuilder() {
    fun build() = OptionalMatchStatement(nodes, patterns)
}
fun optionalMatch(block: OptionalMatchStatementBuilder.() -> Unit)
        = OptionalMatchStatementBuilder().apply(block).build()

infix fun Statement.where(block: WhereClauseBuilder.() -> Unit)
        = this.apply { setWhereClause(WhereClauseBuilder().apply(block).build()) }