// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 45 -> sentence 45
 * NUMBER: 4
 * DESCRIPTION: EXCL_EQ token in compound inequality check x != 0 && x != 100
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val x = 5
    return if (x != 0 && x != 100) "OK" else "NOK"
}
