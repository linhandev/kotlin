// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 13 -> sentence 13
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: member function call may omit argument with a default value
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C {
    fun m(x: Int = 1): Int = x
}

fun case_1() {
    checkSubtype<Int>(C().m())
}
