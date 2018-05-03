class RelationshipTests {
    fun perform() {
        println("RELATIONSHIP TESTS")
        val req2 = create {
            node("c"){} has relationship("m", "MARRIED") {} to node("d"){}
        }
        println(req2)

        val alena = node("a", "Alena") {
            "name" value "Alena"
            "age" value 20
        }

        val boris = node("a", "Boris") {
            "name" value "Boris"
            "age" value 21
        }

        val req3 = create {
            alena has relationship("m", "MARRIED") {} to boris
        }
        println(req3)

        val req4 = create {
            val e = node ("e", "E"){ }
            val f = node ("f", "F"){ }
            e has relationship("rel") {} to f
        }
        println(req4)

        val req5 = create {
            val e = node ("e", "E"){ }
            val f = node ("f", "F"){ }
            e has relationship {} to f
        }
        println(req5)

        val req6 = create {
            node("c"){} has relationship { "type" value "unnamed" } to node("d"){}
        }
        println(req6)
        println()
    }

}
