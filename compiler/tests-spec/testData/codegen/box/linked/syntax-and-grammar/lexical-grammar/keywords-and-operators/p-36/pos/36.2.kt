// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 36 -> sentence 36
 * NUMBER: 2
 * DESCRIPTION: AT_POST_WS token in continue@label jump to outer loop
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var sum = 0
    outer@ for (i in 1..5) {
        for (j in 1..5) {
            if (j == 3) {
                continue@outer
            }
            sum += j
        }
    }
    return if (sum == 15) "OK" else "NOK"
}
