// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: exceptions, catching-exceptions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: catch parameter type must be a subtype of kotlin.Throwable
 */

// TESTCASE NUMBER: 1
fun case_1() {
    try {
    } catch (<!THROWABLE_TYPE_MISMATCH!>e: String<!>) {
    }
}
