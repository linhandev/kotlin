// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 29 -> sentence 29
 * NUMBER: 4
 * DESCRIPTION: ARROW token used in multi-parameter lambda { a, b -> a + b }
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val sum = { a: Int, b: Int -> a + b }
    return if (sum(10, 32) == 42) "OK" else "NOK"
}
