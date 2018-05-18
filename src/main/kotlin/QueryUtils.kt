import QueryObjects.Statement

/* Combining multiple clauses in one query */
operator fun Statement.plus(other: Statement) = other.also { it.setBaseQuery(getFullQuery()) }
infix fun Statement.and(other: Statement) = this + other

/* Additional functions */
infix fun Statement.returns(what: String) = this.apply { setReturnValue(what) }

infix fun Statement.limit(howMany: Any)   = this.apply { setReturnLimit(howMany) }

infix fun Statement.orderBy(how: String)  = this.apply { setOrderStyle(how) }
