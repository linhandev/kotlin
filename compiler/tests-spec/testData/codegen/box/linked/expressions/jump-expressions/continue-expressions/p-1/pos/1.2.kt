// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, jump-expressions, continue-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: continue@Loop jumps to labeled loop next iteration
 */

// TESTCASE NUMBER: 1

fun box(): String {
    var sum = 0
    outer@ for (i in 1..2) {
        for (j in 1..2) {
            if (j == 2) continue@outer
            sum += i * 10 + j
        }
    }
    return if (sum == 32) "OK" else "NOK"
}
