// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 25 -> sentence 25
 *                expressions, call-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: local function call combines vararg and trailing default parameters type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun outer(): Int {
    fun pack(vararg xs: Int, tail: Int = 10): Int = xs.sum() + tail
    return pack(1, 2)
}

// TESTCASE NUMBER: 1
fun test(): Int = outer()

fun case1() {
    checkSubtype<Int>(test())
}
