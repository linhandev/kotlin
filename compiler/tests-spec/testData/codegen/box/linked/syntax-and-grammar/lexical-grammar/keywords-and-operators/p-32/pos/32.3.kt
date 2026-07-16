// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 32 -> sentence 32
 * NUMBER: 3
 * DESCRIPTION: COLONCOLON token used in member function reference Greeter::greet
 */
// TESTCASE NUMBER: 1

class Greeter {
    fun greet(name: String): String = "Hello, $name"
}

fun box(): String {
    val greet = Greeter::greet
    return if (greet(Greeter(), "World") == "Hello, World") "OK" else "NOK"
}
