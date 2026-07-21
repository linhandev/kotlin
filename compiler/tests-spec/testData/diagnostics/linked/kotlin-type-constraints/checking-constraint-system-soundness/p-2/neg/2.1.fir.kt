// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, checking-constraint-system-soundness -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: reduction reports inference error when nullable S is not subtype of non-nullable T
 */

fun requireString1321(value: String) {}

// TESTCASE NUMBER: 1
fun case_1(nullable: String?) {
    requireString1321(<!ARGUMENT_TYPE_MISMATCH!>nullable<!>)
}
