@DslMarker
annotation class QueryContext

@QueryContext
open class StatementBuilder {
    protected val nodes = mutableListOf<Node>()
    protected val patterns = mutableListOf<Pattern>()

    operator fun Node.unaryPlus() { nodes.add(this) }

    private fun addToPatterns(pattern: Pattern) {
        if (pattern.nodeFrom != null && pattern.nodeTo != null) patterns.add(pattern)
    }

    infix fun Node.has(rel: Relationship) = Pattern(rel, nodeFrom = this@has).apply { addToPatterns(this) }
    infix fun Relationship.from(n: Node)  = Pattern(this, nodeFrom = n).apply { nodeFrom = n }
    infix fun Pattern.to(n: Node)         = this.apply { nodeTo = n; addToPatterns(this) }

    infix fun Relationship.to(n: Node)    = Pattern(this).apply { nodeTo = n }
    infix fun Pattern.from(n: Node)       = this.apply { nodeFrom = n; addToPatterns(this) }

    infix fun Relationship.between(node: Node) = this.from(node).apply { isDirected = false }
    infix fun Pattern.and(node: Node)          = to(node).apply { isDirected = false }

    infix fun Pattern.which(backRel: Relationship) = this.apply { backRelationship = backRel }
    infix fun Pattern.by(backNode: Node) = this.apply { backNodeFrom = backNode }

    infix fun Int.from(len: Int) = len..this
    infix fun Int.upTo(len: Int) = this..len

    operator fun Relationship.get(range: IntRange) = copy().apply {
        setMinLength(range.start)
        setMaxLength(range.endInclusive)
    }
    operator fun Relationship.get(len: Int) = copy().apply {
        setMinLength(len)
        setMaxLength(len)
    }

    infix fun Pattern.named(s: String) = this.apply { alias = s }
}
