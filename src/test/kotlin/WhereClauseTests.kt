class WhereClauseTests {
    fun perform() {
        println("WHERE CLAUSE TESTS")
        val a = node("a", "Person") {}
        val b = node("b", "Person") {}

        val req2 = match {
            +a
            +b
        } where {
            + ("n.age" lessThan 30)
        }
        println(req2)
        println()

        val req3 = match {
            +a
            +b
        } where {
            + (("a.age" notEqualTo "b.age") and
                    not(("a.name" contains "K") or ("b.age" greaterThan 2)))
            + not("n.name" endsWith "s")
        }
        println(req3)
        println()


        val req4 = match { + node("n"){} } where {
            + ((("n.name" equals "Peter") xor ("n.age" lessThan 30) and "n.name" equals "Tobias") or
                    not(("n.name" equals "Tobias") or ("n.name" equals "Peter")))
        }
        println(req4)
        println()

    }
}
