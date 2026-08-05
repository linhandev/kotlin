// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: 局部函数声明可带默认参数 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun outer(a: Int): Int {
    fun scale(x: Int, k: Int = a): Int = x * k
    return scale(2)
}

// TESTCASE NUMBER: 1
fun test(): Int = outer(3)

fun case1() {
    checkSubtype<Int>(test())
}
