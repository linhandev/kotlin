// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: runtime-type-information, runtime-available-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: only non-nullable runtime types may be used in class literal expressions
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val c = <!NULLABLE_TYPE_IN_CLASS_LITERAL_LHS!>String?::class<!>
}
