class MultipleClausesTests {
    fun perform() {
        println("MULTIPLE CLAUSES IN STATEMENT TESTS")

        val a = node("a", "Person") {}
        val b = node("b", "Person") {}
        val req1 = match { + a } and create { + b }
        println(req1)
        println()

        val req2 = match { + a } + create { + b }
        println(req2)
        println()

        val req3 = match {
            + a
            + b
        } where {
            + ("n.age" lessThan 30)
        } and create {
                node("a") {} has relationship("r", "RELTYPE") {} to node("b") {}
        } returns "type(r)"

        println(req3)
        println()

        val req4 = match {
            + node("n") {}
        } where {
            + ("n.age" lessThan 30)
        } returns "n.name" orderBy "n.name" limit 3

        println(req4)
        println()
    }
}