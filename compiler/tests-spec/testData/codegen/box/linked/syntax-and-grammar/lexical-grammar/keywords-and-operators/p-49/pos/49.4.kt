// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 49 -> sentence 49
 * NUMBER: 4
 * DESCRIPTION: EQEQEQ token comparing null refs
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val a: IntArray? = null
    val b: IntArray? = null
    return if (a === b) "OK" else "NOK"
}
