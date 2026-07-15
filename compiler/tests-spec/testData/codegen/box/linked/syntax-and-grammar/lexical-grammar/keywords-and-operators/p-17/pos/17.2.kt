// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 17 -> sentence 17
 * NUMBER: 2
 * DESCRIPTION: CONJ token used in short-circuit evaluation
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var counter = 0
    val result = false && (++counter > 0)
    return if (!result && counter == 0) "OK" else "NOK"
}
