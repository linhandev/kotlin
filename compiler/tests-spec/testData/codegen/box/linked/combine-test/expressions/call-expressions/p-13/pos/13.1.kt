/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 13 -> sentence 13
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: member function call may omit argument with a default value
 */

// TESTCASE NUMBER: 1
class C {
    fun m(x: Int = 1): Int = x
}

fun box(): String {
    if (C().m() != 1) return "NOK"
    return "OK"
}
