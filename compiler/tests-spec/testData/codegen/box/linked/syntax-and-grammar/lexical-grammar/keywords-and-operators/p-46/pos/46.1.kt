// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 46 -> sentence 46
 * NUMBER: 1
 * DESCRIPTION: EXCL_EQEQ token in identity inequality intArrayOf(1) !== intArrayOf(1)
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val a = intArrayOf(1)
    val b = intArrayOf(1)
    return if (a !== b) "OK" else "NOK"
}
