// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, code-blocks, coercion-to-kotlin-unit -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: fun case1(): Int = if (true) work() reports INVALID_IF_AS_EXPRESSION and TYPE_MISMATCH
 */

fun work() {}

// TESTCASE NUMBER: 1
fun case1(): Int = <!TYPE_MISMATCH!><!INVALID_IF_AS_EXPRESSION!>if<!> (true) work()<!>
