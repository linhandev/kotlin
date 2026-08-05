// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: 顶层函数默认实参不能前向引用后续形参
 */

// TESTCASE NUMBER: 1
fun f(a: Int = <!UNINITIALIZED_PARAMETER!>b<!>, b: Int = 1): Int = a + b
