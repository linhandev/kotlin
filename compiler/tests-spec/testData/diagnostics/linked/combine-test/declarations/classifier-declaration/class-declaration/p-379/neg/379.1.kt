// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 379 -> sentence 379
 * declarations, declaration-visibility -> paragraph 379 -> sentence 379
 * declarations, function-declaration -> paragraph 379 -> sentence 379
 * NUMBER: 1
 * DESCRIPTION: private fun cannot be called directly from outside class
 */

// TESTCASE NUMBER: 1
class C { private fun secret(): Int = 1 }
fun test(): Int = C().<!INVISIBLE_MEMBER!>secret<!>()
