// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 25 -> sentence 25
 *                declarations, declaration-visibility -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: private member cannot be accessed from interpolation expression outside class
 */

// TESTCASE NUMBER: 1
class C(private val secret: Int = 1)

fun test(c: C): String = "s=${c.<!INVISIBLE_MEMBER!>secret<!>}"
