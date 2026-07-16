// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 49 -> sentence 49
 * NUMBER: 3
 * DESCRIPTION: EQEQEQ token in while condition same ref
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val a = intArrayOf(9)
    var b: IntArray = a
    var count = 0
    while (a === b) {
        count++
        return if (count == 1) "OK" else "NOK"
    }
    return "NOK"
}
