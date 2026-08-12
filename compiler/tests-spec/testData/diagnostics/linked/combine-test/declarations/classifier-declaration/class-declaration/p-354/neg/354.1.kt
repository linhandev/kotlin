// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 354 -> sentence 354
 * declarations, declaration-visibility -> paragraph 354 -> sentence 354
 * declarations, property-declaration -> paragraph 354 -> sentence 354
 * NUMBER: 1
 * DESCRIPTION: private var cannot be modified from outside class
 */

// TESTCASE NUMBER: 1
class C { private var n = 0 }

fun test() { C().<!INVISIBLE_MEMBER!>n<!> = 1 }
