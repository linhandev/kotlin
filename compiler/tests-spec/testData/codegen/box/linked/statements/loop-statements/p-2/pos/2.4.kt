// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 2 -> sentence 2
 * NUMBER: 4
 * DESCRIPTION: do-while-loop body break when i == 2 yields i == 2 at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var i = 0
    do {
        i++
        if (i == 2) break
    } while (true)
    return if (i == 2) "OK" else "NOK"
}
