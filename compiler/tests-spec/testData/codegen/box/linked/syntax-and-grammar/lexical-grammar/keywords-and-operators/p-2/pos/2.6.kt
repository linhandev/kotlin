// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 2 -> sentence 2
 * NUMBER: 6
 * DESCRIPTION: DOT token used with safe call operator (obj?.prop) and with companion object
 */
// TESTCASE NUMBER: 1

class Greeter {
    companion object {
        val greeting: String = "Hello"
    }
    fun greet(name: String): String = "$name!"
}

fun box(): String {
    val greeter: Greeter? = Greeter()
    val result = greeter?.greet("OK") ?: ""
    val companionResult = Greeter.greeting
    return if (result == "OK!" && companionResult == "Hello") "OK" else "NOK"
}
