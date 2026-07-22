// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, code-blocks -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: run { val x = 40; x + 2 } yields 42 at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val result = run {
        val x = 40
        x + 2
    }
    return if (result == 42) "OK" else "NOK"
}
