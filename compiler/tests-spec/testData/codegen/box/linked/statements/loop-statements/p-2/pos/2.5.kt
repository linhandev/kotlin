// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 2 -> sentence 2
 * NUMBER: 5
 * DESCRIPTION: do-while-loop body continue at i == 3 skips iteration and yields sum == 12
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var sum = 0
    var i = 0
    do {
        i++
        if (i == 3) continue
        sum += i
    } while (i < 5)
    return if (sum == 12) "OK" else "NOK"
}
