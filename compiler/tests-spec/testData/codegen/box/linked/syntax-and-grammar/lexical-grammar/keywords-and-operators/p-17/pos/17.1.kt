// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: CONJ token used in logical AND operation a && b
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val a = true
    val b = true
    val result = a && b
    return if (result) "OK" else "NOK"
}
