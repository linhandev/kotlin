// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, jump-expressions, continue-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: continue in while loop body jumps to next iteration
 */

// TESTCASE NUMBER: 1

fun box(): String {
    var i = 0
    var sum = 0
    while (i < 3) {
        i++
        if (i == 2) continue
        sum += i
    }
    return if (sum == 4) "OK" else "NOK"
}
