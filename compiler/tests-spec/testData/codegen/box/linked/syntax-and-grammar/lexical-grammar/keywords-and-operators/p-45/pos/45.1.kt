// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 45 -> sentence 45
 * NUMBER: 1
 * DESCRIPTION: EXCL_EQ token in comparison expression 1 != 2
 */

// TESTCASE NUMBER: 1
fun box(): String {
    return if (1 != 2) "OK" else "NOK"
}
