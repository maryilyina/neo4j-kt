class NodeTests {
    fun perform() {
        println("NODE TESTS")

        val req0 = create {
            + node (label="Cat"){
                "type" value "home"
            }
        }
        println(req0)

        val alena = node("a", "Alena") {
            "name" value "Alena"
            "age" value 20
        }

        val boris = node("a", "Boris") {
            "name" value "Boris"
            "age" value 21
        }
        val req1 = create {
            + alena
            + boris
        }
        println(req1)

        val req2 = create {
            val n = node("p", "Person") {
                for (i in 1..3)
                    "child$i" value i
                "name" value "Alex"
                "age" value 1
                "hero" value true
            }
            val w = node("w", "Person") {}
            +n
            +w

        } returns "p.name"

        println(req2)

        class Restaurant(val name: String, val food: String, val cuisine: String)
        val rest = Restaurant("Harakiri", "Sushi", "Japanese")

        val req3 = create {
            + node("rest", "Restaurant") {
                "name" value rest.name
                "food" value rest.food
                "cuisine" value rest.cuisine
            }
        } returns "rest.name"

        println(req3)

        println()
    }
}

