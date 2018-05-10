/**
 * Specific Cypher statement types
 */
class CreateStatement (nodes: MutableList<Node>, patterns: MutableList<Pattern>) : Statement(nodes, patterns) {
    override val statementName = "CREATE"
}
class MatchStatement (nodes: MutableList<Node>, patterns: MutableList<Pattern>) : Statement(nodes, patterns) {
    override val statementName = "MATCH"
}
class MergeStatement (nodes: MutableList<Node>, patterns: MutableList<Pattern>) : Statement(nodes, patterns) {
    override val statementName = "MERGE"
}
class OptionalMatchStatement (nodes: MutableList<Node>, patterns: MutableList<Pattern>) : Statement(nodes, patterns) {
    override val statementName = "OPTIONAL MATCH"
}

