class DifferentTypesTests {
    fun perform() {
        println("DIFFERENT STATEMENTS TYPES TESTS")
        val req1 = create {
            + node("movie", "Movie") {}
        } returns "movie.title"
        println(req1)

        val req2 = match {
            relationship {} between node("director") {"name" value "Oliver"} and node("movie"){}
        } returns "movie.title"
        println(req2)

        val req3 = optionalMatch {
            relationship(type="ACTED_IN") {} from node(label="Person") {} to node("movie", "Movie"){}
        } returns "movie.title"
        println(req3)

        val acted = relationship(type="ACTED_IN") {}
        val actor = node(label="Person") {}
        val movie = node("movie", "Movie"){}

        val req4 = merge {
            acted from actor to movie named "pathName"
        } returns "pathName"
        println(req4)


        println()
    }
}


