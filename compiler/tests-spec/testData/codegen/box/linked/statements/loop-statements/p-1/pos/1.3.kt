// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: while-loop body break when i == 2 yields i == 2 at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var i = 0
    while (true) {
        i++
        if (i == 2) break
    }
    return if (i == 2) "OK" else "NOK"
}
