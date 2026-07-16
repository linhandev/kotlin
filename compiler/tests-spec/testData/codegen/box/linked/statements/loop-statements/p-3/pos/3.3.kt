// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 3 -> sentence 3
 * NUMBER: 3
 * DESCRIPTION: break at v == 4 yields sum == 6 at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var sum = 0
    for (v in 1..10) {
        if (v == 4) break
        sum += v
    }
    return if (sum == 6) "OK" else "NOK"
}
