import Relationship.PathLength.Ranges.ANY

class RangesTests {
    fun perform() {
        println("------------------RANGES TESTS------------------")
        val c = node("c"){}
        val d = node("d"){}
        val rel = relationship(type="KNOWS") {}

        var req = create {
            c has rel[2 upTo 4] to d
        }
        DSLTest("SpecificLength", req,
                "CREATE (c)-[:KNOWS*2..4]->(d)")


        req = create {
            c has rel[2..4]     to d
            c has rel[2 upTo 4] to d
        }
        DSLTest("FiniteRanges", req,
                "CREATE (c)-[:KNOWS*2..4]->(d), (c)-[:KNOWS*2..4]->(d)")


        req = create {
            c has rel[20..ANY]     to d
            c has rel[ANY from 20] to d
        }
        DSLTest("InfiniteEnd", req,
                "CREATE (c)-[:KNOWS*20..]->(d), (c)-[:KNOWS*20..]->(d)")


        req = create {
            c has rel[ANY upTo 20] to d
            c has rel[1..20]       to d
        }
        DSLTest("DefaultStart", req,
                "CREATE (c)-[:KNOWS*..20]->(d), (c)-[:KNOWS*..20]->(d)")


        req = create {
            c has rel[ANY] to d
            c has rel[0..1] to d
        }
        DSLTest("DefaultStart", req,
                "CREATE (c)-[:KNOWS*]->(d), (c)-[:KNOWS*0..1]->(d)")
    }
}