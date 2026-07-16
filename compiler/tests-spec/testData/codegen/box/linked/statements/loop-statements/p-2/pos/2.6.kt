// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 2 -> sentence 2
 * NUMBER: 6
 * DESCRIPTION: do while (i++ < 3) empty body yields i == 4 at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var i = 0
    do while (i++ < 3)
    return if (i == 4) "OK" else "NOK"
}
