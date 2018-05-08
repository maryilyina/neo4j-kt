import Relationship.Companion.ANY

class RelationshipTests {
    fun perform() {
        println("RELATIONSHIP TESTS")

        RangesTests().perform()
        DirectionTests().perform()

        val alena = node("a", "Alena") { "name" value "Alena"; "age" value 20 }
        val boris = node("a", "Boris") { "name" value "Boris"; "age" value 21 }

        val req3 = create {
            alena has relationship("m", "MARRIED") {} to boris
        }
        println(req3)

        val req4 = create {
            val e = node ("e", "E") {}
            val f = node ("f", "F") {}
            val someRel = relationship("rel") {}
            e has someRel to f
        }
        println(req4)

        val req10 = create {
            node("me") {} has relationship(type="KNOWS") {} [1..2] to node("remote_friend") {}
        }
        println(req10)
        println()
    }

    class DirectionTests {
        fun perform() {
            val e = node ("e", "E") {}
            val f = node ("f", "F") {}

            val req3 = create {
                node("c"){} has relationship { "type" value "unnamed" } to node("d") {}
            }
            println(req3)

            val req4 = create {
                e has relationship {} to f
                relationship {} from e to f
                relationship {} to f from e
                relationship {} between e and f
            }
            println(req4)

            val acted = relationship(type="ACTED_IN") {}
            val filmed = relationship(type="FILMED") {}

            val actor = node(label="Person") {}
            val movie = node("movie", "Movie"){}
            val hollywood = node("hollywood", "Hollywood"){}

            val req5 = create {
                actor has acted to movie which filmed by hollywood
            }
            println(req5)

            val andrew = node("andrew") { "name" value "Andrew" }
            val michael = node("michael") { "name" value "Michael" }
            val worksAt = relationship (type="WORKS_AT"){}
            val req6 = create {
                andrew has worksAt[ANY] to node("neo") {} which worksAt by michael named "p"
            }
            println(req6)
            println()
        }
    }

    class RangesTests {
        fun perform() {
            val req1 = create {
                val c = node("c"){}
                val d = node("d"){}
                val rel = relationship(type="KNOWS") {}
                c has rel[4]    to d
                c has rel[2..4] to d
                c has rel[2 upTo 4]   to d

                c has rel[20..ANY]     to d
                c has rel[ANY from 20] to d

                c has rel[1..20]       to d
                c has rel[ANY upTo 20] to d

                c has rel[ANY] to d

                c has rel[0..1] to d
            }
            println(req1)
            println()
        }
    }

}

