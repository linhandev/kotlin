// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 1 -> sentence 1
 * NUMBER: 7
 * DESCRIPTION: while (i < 3) i++ single statement without braces yields i == 3
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var i = 0
    while (i < 3) i++
    return if (i == 3) "OK" else "NOK"
}
