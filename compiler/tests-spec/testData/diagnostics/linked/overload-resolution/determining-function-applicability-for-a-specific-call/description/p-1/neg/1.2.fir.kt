// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, determining-function-applicability-for-a-specific-call, description -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: not all call arguments assignable to parameters so function is not applicable
 */

fun join11302(a: Int, b: Int): String = "$a$b"

// TESTCASE NUMBER: 1
fun case_1(): String = join11302(1, <!ARGUMENT_TYPE_MISMATCH!>"2"<!>)
