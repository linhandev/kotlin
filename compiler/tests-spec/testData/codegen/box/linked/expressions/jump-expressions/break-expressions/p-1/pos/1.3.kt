// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, jump-expressions, break-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: break in while loop exits loop
 */

// TESTCASE NUMBER: 1

fun box(): String {
    var i = 0
    var sum = 0
    while (i < 3) {
        i++
        if (i == 2) break
        sum += i
    }
    return if (sum == 1) "OK" else "NOK"
}
