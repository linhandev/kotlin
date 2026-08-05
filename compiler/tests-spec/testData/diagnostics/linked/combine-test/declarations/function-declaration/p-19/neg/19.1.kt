// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 19 -> sentence 19
 *                expressions, call-and-property-access-expressions, callable-references -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: 外层作用域外不能取得局部函数的 callable reference
 */

// TESTCASE NUMBER: 1
fun outer() { fun secret(): Int = 1 }

val f: () -> Int = ::<!UNRESOLVED_REFERENCE!>secret<!>

fun test() = f()
