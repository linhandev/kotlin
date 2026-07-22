// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, jump-expressions, continue-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: unlabeled continue jumps to next iteration of innermost loop
 */

// TESTCASE NUMBER: 1

fun box(): String {
    var sum = 0
    for (i in 1..3) {
        if (i == 2) continue
        sum += i
    }
    return if (sum == 4) "OK" else "NOK"
}
