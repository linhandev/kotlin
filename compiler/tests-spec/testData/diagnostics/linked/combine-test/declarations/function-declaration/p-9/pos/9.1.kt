// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: local function default arguments can reference outer parameters type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun outer(limit: Int): Int {
    fun take(n: Int = limit): Int = n
    return take()
}

// TESTCASE NUMBER: 1
fun test(): Int = outer(4)

fun case1() {
    checkSubtype<Int>(test())
}
