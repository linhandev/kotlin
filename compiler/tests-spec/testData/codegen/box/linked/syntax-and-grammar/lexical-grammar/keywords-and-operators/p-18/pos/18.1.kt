// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: DISJ token used in logical OR operation a || b
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val a = false
    val b = true
    val result = a || b
    return if (result) "OK" else "NOK"
}
