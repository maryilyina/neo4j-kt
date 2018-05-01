class NodeBuilder {
    private val data = mutableMapOf<String, Any>()
    infix fun String.value(v: Any) { data[this] =  v }
    fun build(name: String?, label: String?) = Node(name, label, data)
}

class CreateStatementBuilder {
    val nodes = mutableListOf<Node>()
    fun build() : CreateStatement = CreateStatement(nodes)
}

fun CreateStatementBuilder.node(name: String? = null, label: String? = null,
                                block: NodeBuilder.() -> Unit) : Node {
    val b = NodeBuilder()
    b.block()
    nodes.add(b.build(name, label))
    return nodes.last()
}
fun create(block: CreateStatementBuilder.() -> Unit) = CreateStatementBuilder().apply(block).build()