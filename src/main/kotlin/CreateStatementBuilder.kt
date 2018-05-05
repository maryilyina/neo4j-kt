class CreateStatementBuilder {
    val nodes = mutableListOf<Node>()
    val relationships = mutableListOf<Relationship>()
    fun build() : CreateStatement = CreateStatement(nodes, relationships)

    operator fun Any.unaryPlus() {
        if (this is Node) nodes.add(this)
        if (this is Relationship) relationships.add(this)
    }

    fun addToRelationships(rel: Relationship) {
        if (rel.nodeFrom != null && rel.nodeTo != null) relationships.add(rel)
    }

    infix fun Node.has(rel: Relationship) = rel.apply {
        nodeFrom = this@has
        addToRelationships(rel)
    }
    infix fun Relationship.to(n: Node) = this.apply {
        nodeTo = n
        addToRelationships(this)
    }
    infix fun Relationship.from(n: Node) = this.apply {
        nodeFrom = n
        addToRelationships(this)
    }

    operator fun Relationship.get(lenRange: IntRange)
            = copy().apply { pathLengthMin = lenRange.start; pathLengthMax = lenRange.endInclusive }

    operator fun Relationship.get(len: Int)
            = copy().apply { pathLengthMin = len; pathLengthMax = len }
}

fun create(block: CreateStatementBuilder.() -> Unit) = CreateStatementBuilder().apply(block).build()

infix fun CreateStatement.returns(what: String) : CreateStatement = this.apply { ret_val = what }

