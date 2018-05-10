import Relationship.PathLength.Ranges.ANY

class RelationshipTests {
    fun perform() {
        println("------------------RELATIONSHIP TESTS------------------")
        val rel = relationship("m", "MARRIED"){"when" value "longAgo"}
        DSLTest("NamedRelationship", rel, "[m:MARRIED {when: 'longAgo'}]")


        val alena = node("a", "Alena") {}
        val boris = node("b", "Boris") {}
        var req1 = match {
            alena has rel to boris
        }
        DSLTest("MatchRelationship", req1, "MATCH (a:Alena)-[m:MARRIED {when: 'longAgo'}]->(b:Boris)")

        req1 = match {
            alena has relationship { } to boris
        }
        DSLTest("EmptyRelationship", req1, "MATCH (a:Alena)-->(b:Boris)")


        var req2 = create {
            val e = node ("e", "E") {}
            val f = node ("f", "F") {}
            val someRel = relationship(type="REL") {}
            e has someRel to f
        }
        DSLTest("DescribedUnitsInside", req2, "CREATE (e:E)-[:REL]->(f:F)")

        req2 = create {
            node("me") {} has relationship(type="KNOWS"){} [ANY upTo 2] to node("remoteFriend") {}
        }
        DSLTest("FullyDescribed", req2, "CREATE (me)-[:KNOWS*..2]->(remoteFriend)")
    }
}

