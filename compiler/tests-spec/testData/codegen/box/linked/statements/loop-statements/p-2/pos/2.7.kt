// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 2 -> sentence 2
 * NUMBER: 7
 * DESCRIPTION: do i++ while (i < 3) single statement without braces yields i == 3
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var i = 0
    do i++ while (i < 3)
    return if (i == 3) "OK" else "NOK"
}
