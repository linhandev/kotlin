// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: local function in another function cannot be called from outside
 */

// TESTCASE NUMBER: 1
fun host() { fun secret(): Int = 1 }

fun test(): Int = <!UNRESOLVED_REFERENCE!>secret<!>()
