// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 8 -> sentence 8
 * NUMBER: 2
 * DESCRIPTION: LCURL token used in class body class Foo { fun bar() }
 */
// TESTCASE NUMBER: 1

class Greeter(val name: String) {
    fun greet(): String = "Hello, $name"
}

fun box(): String {
    val g = Greeter("Kotlin")
    return if (g.greet() == "Hello, Kotlin") "OK" else "NOK"
}
