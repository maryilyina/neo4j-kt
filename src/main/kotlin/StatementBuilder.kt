import QueryObjects.Node
import QueryObjects.Relationship
import QueryObjects.Pattern

@DslMarker
annotation class QueryContext

/**
 * Abstract DSL builder for query statements
 *
 * Used for describing query nodes and patterns
 *      Nodes are added with "+node"
 *      Patterns are added by special functions when both nodeFrom and nodeTo are described
 */
@QueryContext
abstract class StatementBuilder<out T> {
    /* returns newly created and initialised statement of type T */
    abstract fun build(): T

    protected val nodes = mutableListOf<Node>()
    protected val patterns = mutableListOf<Pattern>()

    private fun addToPatterns(pattern: Pattern) {
        if (pattern.nodeFrom != null && pattern.nodeTo != null) patterns.add(pattern)
    }

    /**
     * Extensions used in DSL
     */

    /**
     * Add node to statement
     */
    operator fun Node.unaryPlus() { nodes.add(this) }

    /**
     *  Add pattern to statement
     */
    /* 1) (a) has [rel] to (b) */
    infix fun Node.has(rel: Relationship) = Pattern(rel, nodeFrom = this@has).apply { addToPatterns(this) }
    infix fun Pattern.to(n: Node)         = this.apply { nodeTo = n; addToPatterns(this) }

    /* 2) [rel] from (a) to (b) */
    infix fun Relationship.from(n: Node)  = Pattern(this, nodeFrom = n).apply { nodeFrom = n }

    /* 3) [rel] to (b) from (a) */
    infix fun Relationship.to(n: Node)    = Pattern(this, nodeTo = n)
    infix fun Pattern.from(n: Node)       = this.apply { nodeFrom = n; addToPatterns(this) }

    /* 4) [rel] between (a) and (b) */
    infix fun Relationship.between(node: Node) = this.from(node).apply { isDirected = false }
    infix fun Pattern.and(node: Node)          = to(node).apply { isDirected = false }

    /* (a) has [rel1] to (b) which [rel2] by (c) */
    infix fun Pattern.which(backRel: Relationship) = this.apply { backRelationship = backRel }
    infix fun Pattern.by(backNode: Node) = this.apply { backNodeFrom = backNode }

    /**
     * Specify relationship length
     */
    /* rel[min..max] */
    operator fun Relationship.get(range: IntRange) = copy().apply {
        setMinLength(range.start)
        setMaxLength(range.endInclusive)
    }
    /* rel[len] */
    operator fun Relationship.get(len: Int) = copy().apply {
        setMinLength(len)
        setMaxLength(len)
    }
    /* rel[ANY from N] == rel[N..ANY] */
    infix fun Int.from(len: Int) = len..this
    /* rel[ANY upTo N] == rel[ANY..N] */
    infix fun Int.upTo(len: Int) = this..len

    /**
     * Name pattern with alias
     */
    infix fun Pattern.named(s: String) = this.apply { alias = s }

}
