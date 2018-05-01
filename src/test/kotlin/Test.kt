import org.neo4j.driver.v1.AuthTokens
import org.neo4j.driver.v1.GraphDatabase

class Example(uri: String, user: String, pwd: String) : AutoCloseable {
    private val driver = GraphDatabase.driver(uri, AuthTokens.basic(user, pwd))
    fun greeting(message: String) {
        val session = driver.session()
        try {
            val greeting = session.writeTransaction { tx ->
                val result = tx.run(
                        create {
                            node {
                                "name" value "Alex"
                                "age" value 1
                                "hero" value true
                                "message" value message
                            }
                    }.toString()
                )
                result.single().get(0).toString()
            }
            print(greeting)
        } catch (e: Exception) { throw e }
    }
    override fun close() = driver.close()
}

fun main(args: Array<String>) {
    try {
        val ex = Example("bolt://localhost:7687", "neo4j", "password")
        ex.greeting("hohoh")
    } catch (e: Exception) {
        print(e.message)
    }
}