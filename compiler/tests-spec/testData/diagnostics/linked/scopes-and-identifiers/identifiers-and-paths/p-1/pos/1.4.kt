// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, identifiers-and-paths -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: this@labeledFirst[0] in String extension reads extension receiver
 */

// TESTCASE NUMBER: 1
fun String.labeledFirst(): Char = this@labeledFirst[0]

fun case1(): Char = "abc".labeledFirst()
