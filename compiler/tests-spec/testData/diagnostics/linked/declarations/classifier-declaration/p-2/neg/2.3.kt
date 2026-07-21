// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: object body with invalid trailing syntax after property initializer is rejected
 */

// TESTCASE NUMBER: 1
object Case1 {
    val value: Int = 1<!SYNTAX!><!>
