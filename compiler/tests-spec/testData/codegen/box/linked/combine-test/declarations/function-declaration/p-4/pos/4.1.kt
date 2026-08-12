// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 4 -> sentence 4
 *                expressions, call-expressions -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: local function can be called from outer body after its declaration
 */

// TESTCASE NUMBER: 1
fun outer(): Int {
    fun helper(): Int = 2
    return helper()
}

// TESTCASE NUMBER: 1
fun test(): Int = outer()

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
