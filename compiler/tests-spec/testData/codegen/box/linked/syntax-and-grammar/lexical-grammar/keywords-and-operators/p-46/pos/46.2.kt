// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 46 -> sentence 46
 * NUMBER: 2
 * DESCRIPTION: EXCL_EQEQ token in when branch distinct array refs
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val a = intArrayOf(1)
    val b = intArrayOf(1)
    when {
        a !== b -> return "OK"
        else -> return "NOK"
    }
}
