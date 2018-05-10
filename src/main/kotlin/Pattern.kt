/**
 * Query pattern used in statements
 *
 * A specified Cypher pattern to describe the shape of the data you are looking for.
 * Standard one - relationship between two nodes:
 *      1) Directed
 *          "(nodeFrom)-[relationship]->(nodeTo)"
 *      2) Both directions are allowed
 *          "(nodeFrom)-[relationship]-(nodeTo)"
 *
 * Or a series of connected nodes and relationships, called a "path":
 *      "(nodeFrom)-[relationship]->(nodeTo)<-[backRelationship]-(backNodeFrom)"
 *
 * If relationship is empty, pattern turns into "(nodeFrom)-->(nodeTo)"
 *
 * Can also be named for later use: "alias = (a)-[r]->(b)"
 */
class Pattern(private val relationship: Relationship,
              var nodeFrom:  Node? = null, var nodeTo: Node? = null) {
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