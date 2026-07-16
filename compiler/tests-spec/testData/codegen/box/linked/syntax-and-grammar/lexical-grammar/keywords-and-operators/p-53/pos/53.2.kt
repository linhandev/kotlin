// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 53 -> sentence 53
 * NUMBER: 2
 * DESCRIPTION: BREAK_AT token in break@outer from nested labeled loops
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var sum = 0
    outer@ for (i in 1..5) {
        for (j in 1..5) {
            sum += j
            if (j == 2) {
                break@outer
            }
        }
    }
    return if (sum == 3) "OK" else "NOK"
}
