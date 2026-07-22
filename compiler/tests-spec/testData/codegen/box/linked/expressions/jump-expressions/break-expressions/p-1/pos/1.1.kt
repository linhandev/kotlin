// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, jump-expressions, break-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: unlabeled break exits innermost enclosing loop
 */

// TESTCASE NUMBER: 1

fun box(): String {
    var sum = 0
    for (i in 1..3) {
        if (i == 2) break
        sum += i
    }
    return if (sum == 1) "OK" else "NOK"
}
