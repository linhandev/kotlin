// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, determining-function-applicability-for-a-specific-call, description -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: declaration-site type constraint violation makes function not applicable
 */

fun <T : Number> need11302N(t: T): String = "ok"

// TESTCASE NUMBER: 1
fun case_1(): String = need11302N(<!TYPE_MISMATCH!>"x"<!>)
