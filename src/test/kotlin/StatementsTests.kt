import QueryObjects.Statement

class StatementsTests {
    fun perform() {
        println("------------------DIFFERENT STATEMENT TYPES TESTS------------------")

        val req: Statement = create {
            + node("movie", "Movie")
        } returns "movie.title"

        DSLTest("Create", req,
                "CREATE (movie:Movie)\nRETURN movie.title")

        val req0 = match {
            relationship() between node("director") {"name" value "Oliver"} and node("movie")
        } returns "movie.title"

        DSLTest("Match", req0,
                "MATCH (director{name:'Oliver'})--(movie)\nRETURN movie.title")


        val acted = relationship(type="ACTED_IN")
        val actor = node(label="Person")
        val movie = node("movie", "Movie")

        val req1 = optionalMatch {
            acted from actor to movie
        } returns "movie.title"

        DSLTest("OptionalMatch", req1,
                "OPTIONAL MATCH (:Person)-[:ACTED_IN]->(movie:Movie)\nRETURN movie.title")


        val req2 = merge {
            acted from actor to movie named "pathName"
        } returns "pathName"

        DSLTest("Merge", req2,
                "MERGE pathName=(:Person)-[:ACTED_IN]->(movie:Movie)\nRETURN pathName")


        println()
    }
}


