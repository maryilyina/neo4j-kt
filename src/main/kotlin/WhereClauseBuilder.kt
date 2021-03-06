import QueryObjects.Statement
import QueryObjects.WhereClause

/**
 * DSL builder for describing statement conditions
 *
 * New conditions are added as "+ (condition)"
 * They are formatted as strings and added to conditions list.
 *
 * Multiple conditions can be combined using boolean operators
 */
@QueryContext
class WhereClauseBuilder {
    private val conditions = mutableListOf<String>()
    fun build() = WhereClause(conditions)

    operator fun String.unaryPlus() = conditions.add(this)

    /* Mathematical operators */
    infix fun String.plus(value: Any)       = "$this + $value"
    infix fun String.minus(value: Any)      = "$this - $value"
    infix fun String.multiplyBy(value: Any) = "$this * $value"
    infix fun String.divideBy(value: Any)   = "$this / $value"
    infix fun String.mod(value: Any)        = "$this % $value"
    infix fun String.pow(value: Any)        = "$this ^ $value"

    /* Comparison operators */
    infix fun String.equals(value: Any)               = "$this = $value"
    infix fun String.notEqualTo(value: Any)           = "$this <> $value"
    infix fun String.lessThan(value: Any)             = "$this < $value"
    infix fun String.greaterThan(value: Any)          = "$this > $value"
    infix fun String.lessOrEqualTo(value: Any)    = "$this <= $value"
    infix fun String.greaterOrEqualTo(value: Any) = "$this >= $value"
    infix fun String.isNull(value: Any)               = "$this IS NULL $value"
    infix fun String.isNotNull(value: Any)            = "$this IS NOT NULL $value"

    /* String-specific comparison operators */
    infix fun String.startsWith(value: String)  = "$this STARTS WITH '$value'"
    infix fun String.endsWith(value: String)    = "$this ENDS WITH '$value'"
    infix fun String.contains(value: String)    = "$this CONTAINS '$value'"

    /* Boolean operators */
    infix fun String.and(other: String) = "($this) AND ($other)"
    infix fun String.or(other: String)  = "($this) OR ($other)"
    infix fun String.xor(other: String)  = "($this) XOR ($other)"
    fun not(condition: String)  = "NOT ($condition)"
}

/**
 * Function for executing code in WhereClauseBuilder context
 */
@QueryContext
infix fun Statement.where(block: WhereClauseBuilder.() -> Unit)
        = this.apply { setWhereClause(WhereClauseBuilder().apply(block).build()) }
