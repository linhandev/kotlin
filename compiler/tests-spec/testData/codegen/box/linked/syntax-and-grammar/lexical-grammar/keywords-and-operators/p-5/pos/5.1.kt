// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: RPAREN token closing function call println("hello")
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val s = "hello".uppercase()
    return if (s == "HELLO") "OK" else "NOK"
}
