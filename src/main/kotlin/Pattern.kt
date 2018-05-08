class Pattern(private val relationship: Relationship) {
    var nodeFrom: Node? = null
    var nodeTo: Node? = null
    var isDirected = true
    var alias: String? = null

    var backRelationship: Relationship? = null
    var backNodeFrom: Node? = null

    override fun toString(): String {
        val rel = if (isDirected) "-$relationship->" else "-$relationship-"

        val pathAlias = if (!alias.isNullOrEmpty()) "$alias=" else ""
        val backRel = if (backRelationship != null && backNodeFrom != null)
            "<-$backRelationship-$backNodeFrom" else ""

        return "$pathAlias$nodeFrom$rel$nodeTo$backRel"
    }

}