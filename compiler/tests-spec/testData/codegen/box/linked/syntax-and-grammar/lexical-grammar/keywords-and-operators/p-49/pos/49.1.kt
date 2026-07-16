// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 49 -> sentence 49
 * NUMBER: 1
 * DESCRIPTION: EQEQEQ token in identity equality same intArrayOf ref
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val a = intArrayOf(1)
    val b = a
    return if (a === b) "OK" else "NOK"
}
