// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 3 -> sentence 3
 * NUMBER: 4
 * DESCRIPTION: continue at v == 3 yields sum == 12 at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var sum = 0
    for (v in 1..5) {
        if (v == 3) continue
        sum += v
    }
    return if (sum == 12) "OK" else "NOK"
}
