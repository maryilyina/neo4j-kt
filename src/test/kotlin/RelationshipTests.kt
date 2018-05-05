import Relationship.Companion.INF

class RelationshipTests {
    fun perform() {
        println("RELATIONSHIP TESTS")
        val req2 = create {
            node("c"){}
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
            val someRel = relationship("rel") {}
            e has someRel to f
        }
        println(req4)

        val e = node ("e", "E"){ }
        val f = node ("f", "F"){ }

        val req5 = create {
            e has relationship {} to f
        }
        println(req5)

        val req6 = create {
            node("c"){} has relationship { "type" value "unnamed" } to node("d"){}
        }
        println(req6)
        println()

        val req7 = create {
            val rel = relationship(type="KNOWS") {}
            //node("c"){} has relationship("n") { length in 2..4 }    to node("d"){}
            //node("c"){} has relationship("n") { length from 2 }    to node("d"){}
            //node("c"){} has relationship("n") { length upTo 5 }    to node("d"){}
            //node("c"){} has relationship("n") { length upTo 5 }    to node("d"){}

            node("c"){} has rel[2]      to node("d"){}
            node("c"){} has rel[2..4]   to node("d"){}
            node("c"){} has rel[0..INF] to node("d"){}
            node("c"){} has rel[0..2]   to node("d"){}
            node("c"){} has rel[2..INF] to node("d"){}
            //node("c"){} has rel[2 + more] to node("d"){}

            //node("c"){} has rel in 2     to node("d"){}
            //node("c"){} has rel in 2..4  to node("d"){}
            //node("c"){} has rel longer 2  to node("d"){}
            //node("c"){} has rel upTo 5 to node("d"){}

            //node("c"){} has rel*2      to node("d"){}
            //node("c"){} has rel*(2..5) to node("d"){}
        }
        println(req7)

        val req8 = create {
            relationship {} from e to f
            relationship {} to f from e
        }
        println(req8)


        val req9 = create {
            val r = relationship("name") {}
            r [2] from e to f
            e has r to f
        }
        println(req9)

        val req10 = create {
            node("me"){} has relationship(type="KNOWS") {} [1..2] to node("remote_friend"){}
        }
        println(req10)
        println()
    }

}

