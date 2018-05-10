open class StatementBuilder {
    val nodes = mutableListOf<Node>()
    val patterns = mutableListOf<Pattern>()

    operator fun Any.unaryPlus() { if (this is Node) nodes.add(this) }

    private fun addToPatterns(pattern: Pattern) {
        if (pattern.nodeFrom != null && pattern.nodeTo != null) patterns.add(pattern)
    }

    infix fun Node.has(rel: Relationship) = Pattern(rel).apply { nodeFrom = this@has; addToPatterns(this) }
    infix fun Relationship.from(n: Node)  = Pattern(this).apply { nodeFrom = n }
    infix fun Pattern.to(n: Node)         = this.apply { nodeTo = n; addToPatterns(this) }

    infix fun Relationship.to(n: Node)    = Pattern(this).apply { nodeTo = n }
    infix fun Pattern.from(n: Node)       = this.apply { nodeFrom = n; addToPatterns(this) }

    infix fun Relationship.between(node: Node) = this.from(node).apply { isDirected = false }
    infix fun Pattern.and(node: Node)          = to(node).apply { isDirected = false }

    operator fun Relationship.get(range: IntRange) = copy().apply {
        setMinLength(range.start)
        setMaxLength(range.endInclusive)
    }

    operator fun Relationship.get(len: Int) = copy().apply {
        setMinLength(len)
        setMaxLength(len)
    }

    infix fun Int.from(len: Int) = len..this
    infix fun Int.upTo(len: Int) = this..len

    infix fun Pattern.named(s: String) = this.apply { alias = s }

    infix fun Pattern.which(backRel: Relationship) = this.apply { backRelationship = backRel }
    infix fun Pattern.by(backNode: Node) = this.apply { backNodeFrom = backNode }
}
