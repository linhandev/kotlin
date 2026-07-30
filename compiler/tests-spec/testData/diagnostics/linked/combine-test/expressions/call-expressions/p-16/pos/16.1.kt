// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 16 -> sentence 16
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: secondary constructor forwarding to primary constructor preserves default parameter values
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C(val x: Int = 0) {
    constructor(s: String) : this(s.toInt())
}

fun case_1() {
    checkSubtype<Int>(C("1").x)
}
