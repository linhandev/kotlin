// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 395 -> sentence 395
 * declarations, declaration-visibility -> paragraph 395 -> sentence 395
 * declarations, function-declaration -> paragraph 395 -> sentence 395
 * declarations, classifier-declaration, companion-object -> paragraph 395 -> sentence 395
 * NUMBER: 1
 * DESCRIPTION: companion private fun cannot be called from outside class
 */

// TESTCASE NUMBER: 1
class Host { companion object { private fun secret(): Int = 1 } }
fun test(): Int = Host.<!INVISIBLE_MEMBER!>secret<!>()
