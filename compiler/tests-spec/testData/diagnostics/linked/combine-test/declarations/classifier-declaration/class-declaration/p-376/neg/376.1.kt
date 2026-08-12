// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 376 -> sentence 376
 * declarations, declaration-visibility -> paragraph 376 -> sentence 376
 * declarations, property-declaration -> paragraph 376 -> sentence 376
 * NUMBER: 1
 * DESCRIPTION: string interpolation of private member fails outside class
 */

// TESTCASE NUMBER: 1
class C(private val secret: Int = 1)
fun test(c: C): String = "${c.<!INVISIBLE_MEMBER!>secret<!>}"
