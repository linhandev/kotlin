// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, code-blocks -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Code block evaluates statements in the order they appear
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val order = StringBuilder()
    run {
        order.append("1")
        order.append("2")
        order.append("3")
    }
    return if (order.toString() == "123") "OK" else "NOK"
}
