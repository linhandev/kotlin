// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 10 -> sentence 10
 *                expressions, call-expressions -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: named arguments can skip middle default parameters in local function call type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun outer(): Int {
    fun g(a: Int, b: Int = 1, c: Int = 2): Int = a + b + c
    return g(10, c = 3)
}

// TESTCASE NUMBER: 1
fun test(): Int = outer()

fun case1() {
    checkSubtype<Int>(test())
}
