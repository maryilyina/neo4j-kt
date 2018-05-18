class NodeTests {
    fun perform() {
        println("------------------NODE TESTS------------------")

        var node = node("cat")
        DSLTest("Named", node, "(cat)")


        node = node(label="Human")
        DSLTest("Labeled", node, "(:Human)")


        node = node {
            "name" value "Andres"
            "sport" value "Brazilian Ju-Jitsu"
        }
        DSLTest("Parametrised", node, "({name: 'Andres', sport: 'Brazilian Ju-Jitsu'})")


        node = node("cat", "Cat"){
            "livesAt" value "home"
        }
        DSLTest("FullyDescribed", node.toString(), "(cat:Cat {livesAt: 'home'})")


        var req = create {
            + node("p", "Person") {
                for (i in 1..3)
                    "child$i" value i
                "name" value "Alex"
                "age" value 1
                "hero" value true
            }
        }
        DSLTest("CreateNode", req, "CREATE (p:Person " +
                "{ child1: 1, child2: 2, child3: 3, name: 'Alex', age: 1, hero: true } )")



        val alena = node("a", "Alena") {
            "name" value "Alena"
        }
        val boris = node("b", "Boris") {
            "name" value "Boris"
        }
        req = create {
            + alena
            + boris
        }
        DSLTest("CreateMultipleNodes", req, "CREATE (a:Alena{name:'Alena'}),  (b:Boris{name:'Boris'})")



        class Restaurant(val name: String, val food: String, val workers: Map<String, Int>)
        val rest = Restaurant("Harakiri", "Sushi", mapOf("chefs" to 1, "cooks" to 10, "waiters" to 70))

        req = create {
            + node("rest", "Restaurant") {
                "name" value rest.name
                "food" value rest.food
                for ((k, v) in rest.workers)
                    k value v
            }
        }
        DSLTest("CreateForClass", req, "CREATE (rest:Restaurant { name:'Harakiri', food: 'Sushi', " +
                "chefs: 1, cooks: 10, waiters: 70})")
    }
}
