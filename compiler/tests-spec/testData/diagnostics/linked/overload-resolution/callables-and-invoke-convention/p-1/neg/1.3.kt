// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: enum entry without invoke operator cannot be called as E.A()
 */

enum class Case1142 {
    A
}

// TESTCASE NUMBER: 1
fun case_1(): Case1142 = Case1142.<!DEBUG_INFO_MISSING_UNRESOLVED, FUNCTION_EXPECTED!>A<!>()
