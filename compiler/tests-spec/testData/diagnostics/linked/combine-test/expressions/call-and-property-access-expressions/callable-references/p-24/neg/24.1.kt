// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 24 -> sentence 24
 *                declarations, declaration-visibility -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: referencing a private member via callable reference from outside the class is invisible, verifying compile-time failure
 */

class C { private fun secret(): Int = 1 }

// TESTCASE NUMBER: 1
fun case1() = C::<!INVISIBLE_MEMBER!>secret<!>
