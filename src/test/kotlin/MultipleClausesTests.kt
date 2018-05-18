class MultipleClausesTests {
    fun perform() {
        println("------------------MULTIPLE CLAUSES IN STATEMENT TESTS------------------")

        val a = node("a", "Person")
        val b = node("b", "Person")
        var req = match { + a } and create { + b }
        DSLTest("UniteWithAnd", req, "MATCH (a:Person)\nCREATE(b:Person)")


        req = match { + a } + create { + b }
        DSLTest("UniteWithPlus", req, "MATCH (a:Person)\nCREATE(b:Person)")


        req = match {
            + a
            + b
        } where {
            + ("n.age" lessThan 30)
        } and create {
                node("a") has relationship("r" , "RELTYPE") to node("b")
        } returns "type(r)"

        DSLTest("WithWhereAndReturn", req, "MATCH (a:Person), (b:Person)\nWHERE n.age < 30" +
                "\nCREATE(a)-[r:RELTYPE]->(b)\nRETURN type(r)")


        req = match {
            + node("n")
        } where {
            + ("n.age" lessThan 30)
        } returns "n.name" orderBy "n.name" limit 3

        DSLTest("ReturnWithOrderAndLimit", req, "MATCH (n) \nWHERE n.age < 30" +
                "\nRETURN n.name \nORDER BY n.name \nLIMIT 3")
    }
}