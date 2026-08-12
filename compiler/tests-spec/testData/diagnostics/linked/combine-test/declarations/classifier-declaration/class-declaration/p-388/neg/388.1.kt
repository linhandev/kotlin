// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 388 -> sentence 388
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 388 -> sentence 388
 *                declarations, function-declaration -> paragraph 388 -> sentence 388
 *                expressions, call-and-property-access-expressions, callable-references -> paragraph 388 -> sentence 388
 * NUMBER: 1
 * DESCRIPTION: callable reference cannot bind to private fun
 */

// TESTCASE NUMBER: 1
class C { private fun secret(): Int = 1 }
fun case1() = C::<!INVISIBLE_MEMBER!>secret<!>
