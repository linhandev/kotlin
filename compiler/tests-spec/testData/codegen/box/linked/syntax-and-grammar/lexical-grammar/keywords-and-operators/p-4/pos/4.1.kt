// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: LPAREN token used in function definition fun f(x: Int)
 */
// TESTCASE NUMBER: 1

fun greet(name: String): String = "Hello, $name"

fun box(): String {
    return if (greet("World") == "Hello, World") "OK" else "NOK"
}
