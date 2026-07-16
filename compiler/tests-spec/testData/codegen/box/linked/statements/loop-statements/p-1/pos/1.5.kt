// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: while-loop body continue at i == 3 skips iteration and yields sum == 12
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var sum = 0
    var i = 0
    while (i < 5) {
        i++
        if (i == 3) continue
        sum += i
    }
    return if (sum == 12) "OK" else "NOK"
}
