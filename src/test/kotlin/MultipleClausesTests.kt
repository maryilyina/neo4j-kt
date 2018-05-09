class MultipleClausesTests {
    fun perform() {
        println("MULTIPLE CLAUSES IN STATEMENT TESTS")

        val req1 = match {
            +node("a", "Person") {}
            +node("b", "Person") {}
        } + create {
                node("a") {} has relationship("r", "RELTYPE") {} to node("b") {}
        } returns "type(r)"

        println(req1)
    }
}
