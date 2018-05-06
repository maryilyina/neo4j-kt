class CreateStatement (nodes: MutableList<Node>, relationships: MutableList<Relationship>)
    : Statement(nodes, relationships) {
    override val statementName = "CREATE"
}

class MatchStatement (nodes: MutableList<Node>, relationships: MutableList<Relationship>)
    : Statement(nodes, relationships) {
    override val statementName = "MATCH"
}

class MergeStatement (nodes: MutableList<Node>, relationships: MutableList<Relationship>)
    : Statement(nodes, relationships) {
    override val statementName = "MERGE"
}

class OptionalMatchStatement (nodes: MutableList<Node>, relationships: MutableList<Relationship>)
    : Statement(nodes, relationships) {
    override val statementName = "OPTIONAL MATCH"
}
