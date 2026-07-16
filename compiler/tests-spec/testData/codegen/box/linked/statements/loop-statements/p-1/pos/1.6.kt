// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, loop-statements -> paragraph 1 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: while (i++ < 3); empty body with semicolon yields i == 4 at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var i = 0
    while (i++ < 3);
    return if (i == 4) "OK" else "NOK"
}
