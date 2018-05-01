fun main(args: Array<String>) {
    val request = create {
        val p = node("p", "Person") {
            for (i in 1..3)
                "child$i" value i
            "name" value "Alex"
            "age" value 1
            "hero" value true
        }
        val w = node("w", "Person") {}
    }

    print(request)
}
