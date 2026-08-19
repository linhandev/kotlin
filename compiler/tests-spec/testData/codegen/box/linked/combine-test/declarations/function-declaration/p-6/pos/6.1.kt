// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: local function can capture outer function parameters
 */

// TESTCASE NUMBER: 1
fun outer(base: Int): Int {
    fun add(x: Int): Int = base + x
    return add(3)
}

// TESTCASE NUMBER: 1
fun test(): Int = outer(10)

fun box(): String {
    if (test() != 13) return "NOK"
    return "OK"
}
