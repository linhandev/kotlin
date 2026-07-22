// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: While-loop with false condition does not evaluate body
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var count = 0
    while (false) {
        count++
    }
    return if (count == 0) "OK" else "NOK"
}
