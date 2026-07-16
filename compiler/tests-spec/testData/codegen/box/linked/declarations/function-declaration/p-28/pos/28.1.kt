// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: local function captures and reflects changes to enclosing variables at runtime
 */

// TESTCASE NUMBER: 1
fun outer(): Int {
    var x = 2

    fun inner(): Int = x

    val before = inner()
    x = 42
    val after = inner()
    return before + after
}

fun box(): String {
    return if (outer() == 44) "OK" else "NOK"
}
