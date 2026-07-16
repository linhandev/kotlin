// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: top-level and local function declarations work at runtime
 */

// TESTCASE NUMBER: 1
fun topLevel(x: Int): Int = x + 1

fun useLocal(): Int {
    fun local(y: Int) = y * 2
    return local(21)
}

fun box(): String {
    return if (topLevel(1) == 2 && useLocal() == 42) "OK" else "NOK"
}
