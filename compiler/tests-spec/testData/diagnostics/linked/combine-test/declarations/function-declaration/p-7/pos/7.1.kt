// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: local function can capture outer function local variables type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun outer(): Int {
    val base = 5
    fun add(x: Int): Int = base + x
    return add(2)
}

// TESTCASE NUMBER: 1
fun test(): Int = outer()

fun case1() {
    checkSubtype<Int>(test())
}
