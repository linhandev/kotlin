// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: init block can use constructor parameter values at runtime
 */

// TESTCASE NUMBER: 1
class Greeter(val name: String) {
    val greeting: String

    init {
        greeting = "Hello, $name"
    }
}

fun box(): String {
    val g = Greeter("Kotlin")
    return if (g.greeting == "Hello, Kotlin") "OK" else "NOK"
}
