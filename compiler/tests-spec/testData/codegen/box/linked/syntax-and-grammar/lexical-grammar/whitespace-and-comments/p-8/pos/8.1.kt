// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: Hidden tokens between tokens on the same line; block and line comments as Hidden preserve binding
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val /* block */ x /* another */ = 804  // line
    return if (x + 0 == 804) "OK" else "NOK"
}
