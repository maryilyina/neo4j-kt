class CreateStatementBuilder {
    val nodes = mutableListOf<Node>()
    val relationships = mutableListOf<Relationship>()
    fun build() : CreateStatement = CreateStatement(nodes, relationships)

    operator fun Any.unaryPlus() {
        if (this is Node) nodes.add(this)
        if (this is Relationship) relationships.add(this)
    }

    infix fun Node.has(rel: Relationship) = rel.apply {
        nodeFrom = this@has
        this@CreateStatementBuilder.relationships.add(rel)
    }
    infix fun Relationship.to(n: Node) = this.apply { nodeTo = n }
}


fun create(block: CreateStatementBuilder.() -> Unit) = CreateStatementBuilder().apply(block).build()

infix fun CreateStatement.returns(what: String) : CreateStatement = this.apply { ret_val = what }

