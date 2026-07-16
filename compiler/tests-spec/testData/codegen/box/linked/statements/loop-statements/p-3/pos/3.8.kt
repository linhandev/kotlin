// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 3 -> sentence 3
 * NUMBER: 8
 * DESCRIPTION: for (v in 1..3) sum += v single statement without braces yields sum == 6
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var sum = 0
    for (v in 1..3) sum += v
    return if (sum == 6) "OK" else "NOK"
}
