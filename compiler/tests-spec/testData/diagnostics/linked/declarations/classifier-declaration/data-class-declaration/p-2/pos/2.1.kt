// FIR_IDENTICAL
// LANGUAGE: +DataObjects
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, data-class-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: data object singleton declaration
 */

// TESTCASE NUMBER: 1
data object Singleton

fun case1() {
    val s: Singleton = Singleton
    val same: Singleton = Singleton
}
