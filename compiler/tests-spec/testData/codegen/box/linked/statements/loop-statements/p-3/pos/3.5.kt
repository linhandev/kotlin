// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 3 -> sentence 3
 * NUMBER: 5
 * DESCRIPTION: for ((_, value) in map) destructuring sums values to 6 at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var sum = 0
    for ((_, value) in mapOf("a" to 1, "b" to 2, "c" to 3)) {
        sum += value
    }
    return if (sum == 6) "OK" else "NOK"
}
