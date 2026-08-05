// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: tailrec 局部函数可递归求阶乘 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun outer(n: Int): Int {
    tailrec fun fact(k: Int, acc: Int): Int = if (k <= 1) acc else fact(k - 1, acc * k)
    return fact(n, 1)
}

// TESTCASE NUMBER: 1
fun test(): Int = outer(5)

fun case1() {
    checkSubtype<Int>(test())
}
