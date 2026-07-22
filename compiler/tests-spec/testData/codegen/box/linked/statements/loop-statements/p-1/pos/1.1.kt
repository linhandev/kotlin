// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: while (i < 3) sums 0+1+2 to 3 at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var sum = 0
    var i = 0
    while (i < 3) {
        sum += i
        i++
    }
    return if (sum == 3) "OK" else "NOK"
}
