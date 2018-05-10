# neo4j-kt
Hate writing queries in strings?

neo4j-kt is a DSL for [Cypher](https://neo4j.com/developer/cypher/) query language for Kotlin and [Neo4j graph database](https://neo4j.com/) users.

When integrated with  [driver library for Java](https://neo4j.com/developer/language-guides/), neo4j-kt is a powerful tool for describing and executing
queries to the graph database. Look at the [Neo4j usage example](https://github.com/maryilyina/neo4j-kt/blob/master/src/test/kotlin/Neo4jWithKtExample.kt).


Describing nodes, relationships and complex queries becomes as easy as a pie:
```kotlin
class Restaurant(val name: String, val food: String, val workers: Map<String, Int>)
      val rest = Restaurant("Harakiri", "Sushi", mapOf("chefs" to 1, "cooks" to 10, "waiters" to 70))

      req = create {
          + node("rest", "Restaurant") {
              "name" value rest.name
              "food" value rest.food
              for ((k, v) in rest.workers)
                  k value v
          }
      }
```

Current available functions:
1. Describing [nodes](https://github.com/maryilyina/neo4j-kt/blob/master/src/test/kotlin/NodeTests.kt)
```kotlin
val node = node("cat", "Cat") {
    "livesAt" value "home"
}
```
2. Describing [relationships](https://github.com/maryilyina/neo4j-kt/blob/master/src/test/kotlin/RelationshipTests.kt)
```kotlin
val rel = relationship("m", "MARRIED") { "when" value "longAgo" }
```

3. Describing patterns with different [length](https://github.com/maryilyina/neo4j-kt/blob/master/src/test/kotlin/RangesTests.kt) and [directions](https://github.com/maryilyina/neo4j-kt/blob/master/src/test/kotlin/DirectionTests.kt)
```kotlin
val req = create {
    node("me") {} has relationship(type="KNOWS") {} [ANY upTo 2] to node("remoteFriend") {}
}
```

4. Pattern for [path series](https://github.com/maryilyina/neo4j-kt/blob/master/src/test/kotlin/PathPatternTests.kt)
```kotlin
val acted = relationship(type="ACTED_IN") {}
val filmed = relationship(type="FILMED") {}
val actor = node(label="Person") {}
val movie = node("movie", "Movie") {}
val hollywood = node("hollywood", "Hollywood") {}

val req = create {
    actor has acted to movie which filmed by hollywood
}
```

4. Combining CREATE, MATCH, MERGE, OPTIONAL MATCH [clauses](https://github.com/maryilyina/neo4j-kt/blob/master/src/test/kotlin/MultipleClausesTests.kt)
```kotlin
val req = match {
    + node("a", "Person") {}
    + node("b", "Person") {}
} where {
    + ("n.age" lessThan 30)
} and create {
        node("a") {} has relationship("r" , "RELTYPE") {} to node("b") {}
} returns "type(r)"
```

5. [WHERE clause](https://github.com/maryilyina/neo4j-kt/blob/master/src/test/kotlin/WhereClauseTests.kt)
```kotlin
val req = match {
    +node("a") {}
    +node("b") {}
} where {
    + (("a.age" notEqualTo "b.age") and not(("a.name" contains "K") or ("b.age" greaterThan 2)))
}
```

6. RETURN, ORDER BY, LIMIT [requirements](https://github.com/maryilyina/neo4j-kt/blob/master/src/test/kotlin/MultipleClausesTests.kt)
```kotlin
val req = match {
    + node("n") {}
} where {
    + ("n.age" lessThan 30)
} returns "n.name" orderBy "n.name" limit 3
```
and other features to make life easier.

To be continued...

