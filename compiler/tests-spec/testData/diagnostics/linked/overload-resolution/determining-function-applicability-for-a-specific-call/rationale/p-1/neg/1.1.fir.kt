// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, determining-function-applicability-for-a-specific-call, rationale -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: function not applicable when argument is not assignable to any parameter
 */

fun pick11301N(a: Int): String = "ok"

// TESTCASE NUMBER: 1
fun case_1(): String = pick11301N(<!ARGUMENT_TYPE_MISMATCH!>"x"<!>)
