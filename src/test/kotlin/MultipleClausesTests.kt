class MultipleClausesTests {
    fun perform() {
        println("MULTIPLE CLAUSES IN STATEMENT TESTS")

        val a = node("a", "Person") {}
        val b = node("b", "Person") {}
        val req1 = match {
            + a
            + b
        } and create {
            node("a") {} has relationship("r", "RELTYPE") {} to node("b") {}
        }

        println(req1)
        println()

        val req2 = match {
            + a
            + b
        } + create {
            node("a") {} has relationship("r", "RELTYPE") {} to node("b") {}
        }

        println(req2)
        println()

        val req3 = match {
            + a
            + b
        } where {
            "n.age" lessThan 30
        } and create {
                node("a") {} has relationship("r", "RELTYPE") {} to node("b") {}
        } returns "type(r)"

        println(req3)
        println()
    }
}