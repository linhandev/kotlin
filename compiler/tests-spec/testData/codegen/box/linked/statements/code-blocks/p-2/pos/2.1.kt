// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, code-blocks -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Code block statements may be separated by semicolons
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var a = 0
    var b = 0
    val sum = run {
        a = 1; b = 2; a + b
    }
    return if (a == 1 && b == 2 && sum == 3) "OK" else "NOK"
}
