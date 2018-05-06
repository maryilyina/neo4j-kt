open class StatementBuilder {
    val nodes = mutableListOf<Node>()
    val relationships = mutableListOf<Relationship>()

    operator fun Any.unaryPlus() { if (this is Node) nodes.add(this) }

    private fun addToRelationships(rel: Relationship) {
        if (rel.nodeFrom != null && rel.nodeTo != null) relationships.add(rel)
    }

    infix fun Node.has(rel: Relationship) = rel.apply { nodeFrom = this@has; addToRelationships(this) }
    infix fun Relationship.to(n: Node)    = this.apply { nodeTo = n; addToRelationships(this) }
    infix fun Relationship.from(n: Node)  = this.apply { nodeFrom = n; addToRelationships(this) }

    infix fun Relationship.between(node: Node) = from(node).apply { isDirected = false }
    infix fun Relationship.and(node: Node)     = to(node).apply   { isDirected = false }

    operator fun Relationship.get(lenRange: IntRange) = copy().apply {
        minHops = lenRange.start
        maxHops = lenRange.endInclusive
    }

    operator fun Relationship.get(len: Int) = copy().apply {
        minHops = len
        maxHops = len
    }

    infix fun Int.from(len: Int) = len..this
    infix fun Int.upTo(len: Int) = this..len

    infix fun Relationship.named(s: String) = copy().apply { alias = s }

    infix fun Relationship.which(backRel: Relationship) = this.apply { backRelationship = backRel }
    infix fun Relationship.by(backNode: Node) = this.apply { backNodeFrom = backNode }
}

infix fun Statement.returns(what: String) : Statement = this.apply { returnValue = what }

