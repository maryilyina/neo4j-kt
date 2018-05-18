class WhereClauseTests {
    fun perform() {
        println("------------------WHERE CLAUSE TESTS------------------")
        val a = node("a")
        val b = node("b")

        var req = match {
            + a
        } where {
            + ("a.age" greaterOrEqualTo 30)
        }
        DSLTest("Maths", req,"MATCH (a)\nWHERE a.age >= 30")

        req = match {
            +a
            +b
        } where {
            + (("a.age" notEqualTo "b.age") and
                    not(("a.name" contains "K") or ("b.age" greaterThan 2)))
            + not("n.name" endsWith "s")
        }
        DSLTest("Maths", req,"MATCH (a),(b)\nWHERE (a.age <> b.age) " +
                "AND (NOT ((a.name CONTAINS 'K') OR (b.age > 2)))\n" +
                "WHERE NOT (n.name ENDS WITH 's')")


        req = match { + node("n") } where {
            + ((("n.name" equals "Peter") xor (("n.age" lessThan 30) and ("n.name" equals "Tobias"))) or
                    not(("n.name" equals "Tobias") or ("n.name" equals "Peter")))
        }
        DSLTest("Maths", req,"MATCH (n)\n" +
                "WHERE ((n.name = Peter) XOR ((n.age < 30) AND (n.name = Tobias)))" +
                " OR (NOT ((n.name = Tobias) OR (n.name = Peter)))")
    }
}
