class CreateStatementBuilder : StatementBuilder() {
    fun build() = CreateStatement(nodes, relationships)
}
fun create(block: CreateStatementBuilder.() -> Unit) = CreateStatementBuilder().apply(block).build()


class MatchStatementBuilder : StatementBuilder() {
    fun build() = MatchStatement(nodes, relationships)
}
fun match(block: MatchStatementBuilder.() -> Unit) = MatchStatementBuilder().apply(block).build()


class MergeStatementBuilder : StatementBuilder() {
    fun build() = MergeStatement(nodes, relationships)
}
fun merge(block: MergeStatementBuilder.() -> Unit) = MergeStatementBuilder().apply(block).build()


class OptionalMatchStatementBuilder : StatementBuilder() {
    fun build() = OptionalMatchStatement(nodes, relationships)
}
fun optionalMatch(block: OptionalMatchStatementBuilder.() -> Unit) = OptionalMatchStatementBuilder().apply(block).build()
