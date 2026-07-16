// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, constant-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: const val initialized with runtime function call reports CONST_VAL_WITH_NON_CONST_INITIALIZER
 */

fun runtime(): Int = 1

// TESTCASE NUMBER: 1
const val x = <!CONST_VAL_WITH_NON_CONST_INITIALIZER!>runtime()<!>
