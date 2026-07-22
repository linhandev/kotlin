// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: g.greet("x") calls member function greet with receiver g
 */

// TESTCASE NUMBER: 1

class Greeter(val prefix: String) {
    fun greet(name: String): String = "$prefix$name"
}

fun box(): String {
    val g = Greeter("hi ")
    if (g.greet("x") != "hi x") return "NOK"
    return "OK"
}
