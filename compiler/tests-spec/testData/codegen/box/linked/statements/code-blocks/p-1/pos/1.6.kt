// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, code-blocks -> paragraph 1 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: Code block with no last expression has kotlin.Unit value
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val result: Unit = run {
        var x = 0
        x = 1
    }
    return if (result is Unit) "OK" else "NOK"
}
