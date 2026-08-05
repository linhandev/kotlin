// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 18 -> sentence 18
 *                expressions, call-expressions -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: 同一外层体内局部函数 :: 引用可传给高阶调用 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun outer(xs: List<Int>): Int {
    fun add(a: Int, b: Int): Int = a + b
    return xs.reduce(::add)
}

// TESTCASE NUMBER: 1
fun test(): Int = outer(listOf(1, 2, 3))

fun case1() {
    checkSubtype<Int>(test())
}
