// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 20 -> sentence 20
 * NUMBER: 3
 * DESCRIPTION: EXCL_NO_WS token used in negated boolean literal !true
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val result = !true
    return if (!result) "OK" else "NOK"
}
