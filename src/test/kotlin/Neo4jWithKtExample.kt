import org.neo4j.driver.v1.AuthTokens
import org.neo4j.driver.v1.GraphDatabase

class Neo4jWithKtExample(uri: String, user: String, pwd: String) : AutoCloseable {
    private val driver = GraphDatabase.driver(uri, AuthTokens.basic(user, pwd))
    fun greeting(message: String) {
        val session = driver.session()
        try {
            val greeting = session.writeTransaction { tx ->
                val query = create {
                    + node("alex") {
                        "name" value "Alex"
                        "age" value 20
                        "hero" value true
                        "message" value message
                    }
                } returns "alex.message"
                val result = tx.run(query.toString())
                result.single().get(0).toString()
            }
            print(greeting)
        } catch (e: Exception) { throw e }
    }
    override fun close() = driver.close()
}

fun main(args: Array<String>) {
    try {
        val ex = Neo4jWithKtExample("bolt://localhost:7687", "neo4j", "password")
        ex.greeting("Hello!")
    } catch (e: Exception) {
        print(e.message)
    }
}