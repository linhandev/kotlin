// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 352 -> sentence 352
 * declarations, declaration-visibility -> paragraph 352 -> sentence 352
 * declarations, property-declaration -> paragraph 352 -> sentence 352
 * NUMBER: 1
 * DESCRIPTION: private val cannot be accessed from outside class
 */

// TESTCASE NUMBER: 1
class C { private val secret = 1 }

fun test() = C().<!INVISIBLE_MEMBER!>secret<!>
