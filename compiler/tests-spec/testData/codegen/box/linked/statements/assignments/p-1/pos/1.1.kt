// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: var x = 1; x = 2 yields x == 2 at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var x = 1
    x = 2
    return if (x == 2) "OK" else "NOK"
}
