// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: enum class values function for backwards compatibility
 */

// TESTCASE NUMBER: 1
enum class State { A, B }

fun case1() {
    val arr = State.values()
}
