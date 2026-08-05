// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: 内层局部函数可捕获中层与外层变量 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun outer(a: Int): Int {
    fun mid(b: Int): Int {
        fun inner(c: Int): Int = a + b + c
        return inner(1)
    }
    return mid(2)
}

// TESTCASE NUMBER: 1
fun test(): Int = outer(10)

fun case1() {
    checkSubtype<Int>(test())
}
