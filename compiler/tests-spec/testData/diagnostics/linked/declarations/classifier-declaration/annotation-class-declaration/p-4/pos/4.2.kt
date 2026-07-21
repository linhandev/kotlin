// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, annotation-class-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: annotation class direct instantiation
 */

// TESTCASE NUMBER: 1
annotation class Super(val x: Int)

fun case1() {
    val instance = Super(4)
}
