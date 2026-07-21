// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 9 -> sentence 9
 * NUMBER: 2
 * DESCRIPTION: expression body must satisfy declared return type
 */

// TESTCASE NUMBER: 1
fun mismatch(): String = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
