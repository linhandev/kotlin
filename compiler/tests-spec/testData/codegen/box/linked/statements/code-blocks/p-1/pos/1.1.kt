// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, code-blocks -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Empty code block evaluates to kotlin.Unit
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val result = run { }
    return if (result is Unit) "OK" else "NOK"
}
