// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 391 -> sentence 391
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 391 -> sentence 391
 *                declarations, function-declaration -> paragraph 391 -> sentence 391
 *                inheritance, overriding -> paragraph 391 -> sentence 391
 * NUMBER: 1
 * DESCRIPTION: 覆盖成员函数不能收窄为 private
 */

// TESTCASE NUMBER: 1
open class Base { open fun f(): Int = 1 }
class Sub : Base() { <!CANNOT_WEAKEN_ACCESS_PRIVILEGE, INCOMPATIBLE_MODIFIERS!>private<!> <!INCOMPATIBLE_MODIFIERS!>override<!> fun f(): Int = 2 }
fun test() = Sub().<!INVISIBLE_MEMBER!>f<!>()
