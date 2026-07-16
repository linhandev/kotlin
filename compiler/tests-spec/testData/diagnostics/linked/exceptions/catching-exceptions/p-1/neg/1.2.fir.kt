// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: exceptions, catching-exceptions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: catch parameter with non-reified type parameter is not a valid Throwable type
 */

// TESTCASE NUMBER: 1
fun <T> case_1() {
    try {
    } catch (<!THROWABLE_TYPE_MISMATCH, TYPE_PARAMETER_IN_CATCH_CLAUSE!>e: T<!>) {
    }
}
