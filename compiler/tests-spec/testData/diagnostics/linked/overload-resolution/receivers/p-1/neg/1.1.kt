// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, receivers -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: implicit this is not available in top-level function scope
 */

// TESTCASE NUMBER: 1
fun case_1(): Receiver1101 = <!NO_THIS!>this<!>

class Receiver1101
