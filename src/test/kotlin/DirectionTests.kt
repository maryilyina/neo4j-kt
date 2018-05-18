class DirectionTests {
    fun perform() {
        println("------------------DIRECTION TESTS------------------")
        val e = node("e", "E") {}
        val f = node("f", "F") {}

        var req = match {
            e has relationship("rel") to f
        }
        DSLTest("DirectedType1", req, "MATCH (e:E)-[rel]->(f:F)")


        req = match {
            relationship("rel") from e to f
        }
        DSLTest("DirectedType2", req, "MATCH (e:E)-[rel]->(f:F)")


        req = match {
            relationship("rel") to f from e
        }
        DSLTest("DirectedType3", req, "MATCH (e:E)-[rel]->(f:F)")


        req = match {
            relationship("rel") between e and f
        }
        DSLTest("UndirectedType", req, "MATCH (e:E)-[rel]-(f:F)")
    }
}