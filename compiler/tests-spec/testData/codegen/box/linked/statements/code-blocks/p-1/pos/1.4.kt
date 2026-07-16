// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, code-blocks -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: Lambda code block body value is the last expression when present
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val compute: () -> Int = {
        var x = 1
        x + 1
    }
    return if (compute() == 2) "OK" else "NOK"
}
