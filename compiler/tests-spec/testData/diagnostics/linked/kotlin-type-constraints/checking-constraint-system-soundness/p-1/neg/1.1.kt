// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -FINAL_UPPER_BOUND
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, checking-constraint-system-soundness -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: unsound constraint system has no satisfying substitution for inference variable
 */

fun <T : String> onlyString1321(t: T): T = t

// TESTCASE NUMBER: 1
fun case_1() {
    onlyString1321(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>)
}
