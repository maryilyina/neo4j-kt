import QueryObjects.Node
import QueryObjects.Relationship

/**
 * Abstract builder for nodes or relationships
 */
abstract class UnitBuilder<out T> {
    protected val data = mutableMapOf<String, Any>()
    abstract fun build(name: String?, label: String?) : T
    infix fun String.value(v: Any) { data[this] =  v }
}

@QueryContext
class NodeBuilder : UnitBuilder<Node>() {
    override fun build(name: String?, label: String?) = Node(name, label, data)
}
@QueryContext
class RelationshipBuilder : UnitBuilder<Relationship>() {
    override fun build(name: String?, type: String?) = Relationship(name, type, data)
}

/**
 * Functions for executing code in UnitBuilder context
 */
fun relationship(name: String? = null, type: String? = null, block: RelationshipBuilder.() -> Unit = {}): Relationship {
    val b = RelationshipBuilder()
    b.block()
    return b.build(name, type)
}

fun node(name: String? = null, label: String? = null, block: NodeBuilder.() -> Unit = {}) : Node {
    val b = NodeBuilder()
    b.block()
    return b.build(name, label)
}
