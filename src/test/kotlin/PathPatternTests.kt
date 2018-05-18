import QueryObjects.Relationship.PathLength.Ranges.ANY

class PathPatternTests {
    fun perform() {
        println("------------------DIRECTION TESTS------------------")
        val acted = relationship(type="ACTED_IN")
        val filmed = relationship(type="FILMED")

        val actor = node(label="Person")
        val movie = node("movie", "Movie")
        val hollywood = node("hollywood", "Hollywood")

        val req1 = create {
            actor has acted to movie which filmed by hollywood
        }
        DSLTest("PathPattern", req1,
                "CREATE (:Person)-[:ACTED_IN]->(movie:Movie)<-[:FILMED]-(hollywood:Hollywood)")


        val andrew = node("andrew")
        val michael = node("michael")
        val worksAt = relationship (type="WORKS_AT")

        val req2 = create {
            andrew has worksAt[ANY] to node("JetBrains", "Company") which worksAt by michael named "p"
        }
        DSLTest("PathPatternFullAndNamed", req2,
                "CREATE p = (andrew)-[:WORKS_AT*]->(JetBrains:Company)<-[:WORKS_AT]-(michael)")

    }


}