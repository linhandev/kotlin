// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 365 -> sentence 365
 * declarations, declaration-visibility -> paragraph 365 -> sentence 365
 * declarations, property-declaration -> paragraph 365 -> sentence 365
 * NUMBER: 1
 * DESCRIPTION: 同文件顶层函数仍不能访问类 private 属性
 */

// TESTCASE NUMBER: 1
class C(private val secret: Int)
fun expose(c: C): Int = c.<!INVISIBLE_MEMBER!>secret<!>
fun test() = expose(C(1))
