class RelationshipBuilder {
    private val data = mutableMapOf<String, Any>()
    infix fun String.value(v: Any) { data[this] =  v }
    fun build(name: String?, type: String?) = Relationship(name, type, data)
}

fun relationship(name: String? = null, type: String? = null,
                 block: RelationshipBuilder.() -> Unit): Relationship {
    val b = RelationshipBuilder()
    b.block()
    return b.build(name, type)
}