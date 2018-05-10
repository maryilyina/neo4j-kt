/**
 * DSL builders for specific statement types
 */
class CreateStatementBuilder : StatementBuilder<CreateStatement>() {
    override fun build() = CreateStatement(nodes, patterns)
}
class MatchStatementBuilder : StatementBuilder<MatchStatement>() {
    override fun build() = MatchStatement(nodes, patterns)
}
class MergeStatementBuilder : StatementBuilder<MergeStatement>() {
    override fun build() = MergeStatement(nodes, patterns)
}
class OptionalMatchStatementBuilder : StatementBuilder<OptionalMatchStatement>() {
    override fun build() = OptionalMatchStatement(nodes, patterns)
}

/**
 * Functions for executing code in StatementBuilder context
 */
fun create(block: CreateStatementBuilder.() -> Unit) = CreateStatementBuilder().apply(block).build()

fun match(block: MatchStatementBuilder.() -> Unit) = MatchStatementBuilder().apply(block).build()

fun merge(block: MergeStatementBuilder.() -> Unit) = MergeStatementBuilder().apply(block).build()

fun optionalMatch(block: OptionalMatchStatementBuilder.() -> Unit) = OptionalMatchStatementBuilder().apply(block).build()
