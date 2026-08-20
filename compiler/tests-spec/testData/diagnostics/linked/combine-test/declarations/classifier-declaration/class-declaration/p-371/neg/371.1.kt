// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 371 -> sentence 371
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 371 -> sentence 371
 *                declarations, property-declaration -> paragraph 371 -> sentence 371
 *                inheritance, overriding -> paragraph 371 -> sentence 371
 * NUMBER: 1
 * DESCRIPTION: overriding property cannot narrow protected val to private
 */

// TESTCASE NUMBER: 1
open class Base { protected open val x: Int = 1 }
class Sub : Base() { <!CANNOT_WEAKEN_ACCESS_PRIVILEGE, INCOMPATIBLE_MODIFIERS!>private<!> <!INCOMPATIBLE_MODIFIERS!>override<!> val x: Int = 2 }
fun test() = Sub().<!INVISIBLE_MEMBER!>x<!>
