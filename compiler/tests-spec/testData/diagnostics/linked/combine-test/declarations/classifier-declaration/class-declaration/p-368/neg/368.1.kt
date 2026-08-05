// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 368 -> sentence 368
 * declarations, declaration-visibility -> paragraph 368 -> sentence 368
 * declarations, property-declaration -> paragraph 368 -> sentence 368
 * NUMBER: 1
 * DESCRIPTION: private 自定义 getter 仍禁止类外访问
 */

// TESTCASE NUMBER: 1
class C { private val v: Int get() = 1 }
fun test(): Int = C().<!INVISIBLE_MEMBER!>v<!>
