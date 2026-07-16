// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: DOUBLE_ARROW token in block comment /* => */ does not break parsing; lambda => after comment runs
 */

// TESTCASE NUMBER: 1
fun box(): String {
    /* DOUBLE_ARROW token => appears in block comment */
    val increment = { value: Int -> value + 1 }
    return if (increment(41) == 42) "OK" else "NOK"
}
