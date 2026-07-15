// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 46 -> sentence 46
 * NUMBER: 4
 * DESCRIPTION: EXCL_EQEQ token in if guard for distinct intArrayOf refs
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val left = intArrayOf(10, 20)
    val right = intArrayOf(10, 20)
    return if (left !== right) "OK" else "NOK"
}
