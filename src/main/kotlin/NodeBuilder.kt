class NodeBuilder {
    private val data = mutableMapOf<String, Any>()
    infix fun String.value(v: Any) { data[this] =  v }
    fun build(name: String?, label: String?) = Node(name, label, data)
}

fun node(name: String? = null, label: String? = null,
         block: NodeBuilder.() -> Unit) : Node {
    val b = NodeBuilder()
    b.block()
    return b.build(name, label)
}
