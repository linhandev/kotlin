// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 18 -> sentence 18
 * NUMBER: 2
 * DESCRIPTION: DISJ token used in short-circuit evaluation
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var counter = 0
    val result = true || (++counter > 0)
    return if (result && counter == 0) "OK" else "NOK"
}
