// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, jump-expressions, break-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: break@Loop exits labeled loop
 */

// TESTCASE NUMBER: 1

fun box(): String {
    var sum = 0
    outer@ for (i in 1..3) {
        for (j in 1..3) {
            if (j == 2) break@outer
            sum += i * 10 + j
        }
    }
    return if (sum == 11) "OK" else "NOK"
}
