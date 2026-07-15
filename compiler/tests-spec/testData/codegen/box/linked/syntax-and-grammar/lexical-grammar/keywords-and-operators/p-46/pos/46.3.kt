// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 46 -> sentence 46
 * NUMBER: 3
 * DESCRIPTION: EXCL_EQEQ token in while condition comparing distinct refs
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val a = intArrayOf(1)
    var b: IntArray = intArrayOf(2)
    while (a !== b) {
        if (b[0] == 2) {
            b = intArrayOf(1)
            if (a !== b) {
                return "OK"
            }
        }
        return "NOK"
    }
    return "NOK"
}
