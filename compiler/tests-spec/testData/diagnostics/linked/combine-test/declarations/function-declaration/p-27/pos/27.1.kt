// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 27 -> sentence 27
 *                declarations, property-declaration -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: local function can be declared and called inside property getter type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
val total: Int get() {
    fun sum(a: Int, b: Int): Int = a + b
    return sum(2, 3)
}

// TESTCASE NUMBER: 1
fun test(): Int = total

fun case1() {
    checkSubtype<Int>(test())
}
