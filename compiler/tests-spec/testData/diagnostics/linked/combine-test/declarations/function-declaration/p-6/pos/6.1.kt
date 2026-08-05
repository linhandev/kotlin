// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: 局部函数可捕获外层函数形参 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun outer(base: Int): Int {
    fun add(x: Int): Int = base + x
    return add(3)
}

// TESTCASE NUMBER: 1
fun test(): Int = outer(10)

fun case1() {
    checkSubtype<Int>(test())
}
