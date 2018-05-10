class DSLTest(val name: String, val smth: Any, val expected: String) {
    val res = compare()

    private fun cut(str: String) =  str.replace(" ", "").replace("\r", "")
    private fun compare() {
        println("TEST $name")
        println("QUERY: $smth")
        val e1 = cut(expected)
        val e2 = cut(smth.toString())

        if (e1.contentEquals(e2)) println("SUCCESS")
        else println("FAIL, expected: $e1 \nfound: $e2")
        println()
    }
}

fun main(args: Array<String>) {
    NodeTests().perform()
    RelationshipTests().perform()
    RangesTests().perform()
    DirectionTests().perform()
    PathPatternTests().perform()
    StatementsTests().perform()
    MultipleClausesTests().perform()
    WhereClauseTests().perform()
}
