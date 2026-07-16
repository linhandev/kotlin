// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, annotation-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: annotation class with property constructor parameter
 */

// TESTCASE NUMBER: 1
annotation class Ann(val x: Int)
