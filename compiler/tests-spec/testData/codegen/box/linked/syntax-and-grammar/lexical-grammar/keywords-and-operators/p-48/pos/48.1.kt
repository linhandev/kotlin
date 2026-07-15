// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 48 -> sentence 48
 * NUMBER: 1
 * DESCRIPTION: EQEQ token in comparison expression 1 == 1
 */

// TESTCASE NUMBER: 1
fun box(): String {
    return if (1.toString() == "1") "OK" else "NOK"
}
